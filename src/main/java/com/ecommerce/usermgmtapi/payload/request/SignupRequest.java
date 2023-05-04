package com.ecommerce.usermgmtapi.payload.request;

import com.ecommerce.usermgmtapi.constants.ErrorMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {

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
    
    @NotBlank(message = ErrorMessages.PASSWORD_REQUIRED)
    private String password;

}
