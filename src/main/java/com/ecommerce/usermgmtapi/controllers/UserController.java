package com.ecommerce.usermgmtapi.controllers;

import com.ecommerce.usermgmtapi.domain.User;
import com.ecommerce.usermgmtapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/v1/register")
    public User register(@Valid @RequestBody User user) {
        return this.userService.save(user);
    }

}
