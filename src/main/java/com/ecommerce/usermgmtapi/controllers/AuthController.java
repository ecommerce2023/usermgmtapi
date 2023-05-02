package com.ecommerce.usermgmtapi.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usermgmtapi.constants.ErrorMessages;
import com.ecommerce.usermgmtapi.models.ERole;
import com.ecommerce.usermgmtapi.models.Role;
import com.ecommerce.usermgmtapi.models.User;
import com.ecommerce.usermgmtapi.payload.request.LoginRequest;
import com.ecommerce.usermgmtapi.payload.request.SignupRequest;
import com.ecommerce.usermgmtapi.payload.response.UserInfoResponse;
import com.ecommerce.usermgmtapi.repositories.RoleRepository;
import com.ecommerce.usermgmtapi.repositories.UserRepository;
import com.ecommerce.usermgmtapi.security.jwt.JwtUtils;
import com.ecommerce.usermgmtapi.security.services.UserDetailsImpl;
import com.ecommerce.usermgmtapi.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserService userService;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new UserInfoResponse(userDetails.getUserId(), userDetails.getEmail(), roles));
	}
	
	
	@PostMapping("/signup")
	public User register(@Valid @RequestBody SignupRequest signupRequest) {
		Set<Role> roles = new HashSet<>();
		Role role = roleRepository.findByRoleName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException(ErrorMessages.ROLE_NOT_FOUND));
		roles.add(role);
		User user = new User(signupRequest);
		user.setRoles(roles);
		user.setPassword(encoder.encode(user.getPassword()));
		return this.userService.save(user);
	}
	
}
