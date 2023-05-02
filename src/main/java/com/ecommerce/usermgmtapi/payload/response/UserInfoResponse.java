package com.ecommerce.usermgmtapi.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class UserInfoResponse {

	private Long userId;
	private String email;
	private List<String> roles;

	public UserInfoResponse(Long userId, String email, List<String> roles) {
		this.userId = userId;
		this.email = email;
		this.roles = roles;
	}
}
