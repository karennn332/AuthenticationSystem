package com.practicas.authenticationSystem.service;



import org.springframework.http.ResponseEntity;

import com.practicas.authenticationSystem.dto.LoginRequest;
import com.practicas.authenticationSystem.dto.Request;
import com.practicas.authenticationSystem.dto.Response;


public interface UserService {
	ResponseEntity<Response> signup(Request request);
	ResponseEntity<Response> login(LoginRequest request);
	Response sendOtp();
	Response validataOtp();
	Response resetPassword();
	Response changePassword();

}
