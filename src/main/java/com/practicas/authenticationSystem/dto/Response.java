package com.practicas.authenticationSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
	
	private int statusCode;
	private String responseMessage;
	private UserInfo userInfo;
	
	
	}
	
	
	
	


