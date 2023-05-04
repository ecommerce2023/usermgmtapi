package com.ecommerce.usermgmtapi.models;

import com.ecommerce.usermgmtapi.constants.ErrorMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Enumerated(EnumType.STRING)
	@NotBlank(message = ErrorMessages.ROLE_NAME_REQUIRED)
	private ERole roleName;

	private Date createdOn = new Date();
}
