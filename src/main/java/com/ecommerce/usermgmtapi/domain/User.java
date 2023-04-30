package com.ecommerce.usermgmtapi.domain;

import com.ecommerce.usermgmtapi.constants.ErrorMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

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

    @PreUpdate
    protected void onUpdate() {
        lastModifiedOn = new Date();
    }

}
