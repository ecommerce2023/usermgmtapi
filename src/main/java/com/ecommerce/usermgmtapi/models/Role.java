package com.ecommerce.usermgmtapi.models;

import java.util.Date;

import com.ecommerce.usermgmtapi.constants.ErrorMessages;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
