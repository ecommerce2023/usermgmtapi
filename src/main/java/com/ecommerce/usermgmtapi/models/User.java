package com.ecommerce.usermgmtapi.models;

import com.ecommerce.usermgmtapi.constants.ErrorMessages;
import com.ecommerce.usermgmtapi.payload.request.SignupRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "\"user\"")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = ErrorMessages.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = ErrorMessages.LAST_NAME_REQUIRED)
    private String lastName;

    @NotBlank(message = ErrorMessages.MOBILE_REQUIRED)
    private String mobile;

    @NotBlank(message = ErrorMessages.EMAIL_REQUIRED)
    @Email(message = ErrorMessages.EMAIL_IS_INVALID)
    private String email;

    private String alternateMobile;

    private String password;

    private Boolean isActive = true;

    private Date createdOn = new Date();

    private Date lastModifiedOn;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_map",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(SignupRequest signupRequest) {
        this.email = signupRequest.getEmail();
        this.password = signupRequest.getPassword();
        this.alternateMobile = signupRequest.getAlternateMobile();
        this.mobile = signupRequest.getMobile();
        this.firstName = signupRequest.getFirstName();
        this.lastName = signupRequest.getLastName();
    }

    @PreUpdate
    protected void onUpdate() {
        lastModifiedOn = new Date();
    }

}
