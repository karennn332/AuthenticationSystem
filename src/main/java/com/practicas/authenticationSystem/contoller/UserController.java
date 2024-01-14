package com.practicas.authenticationSystem.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.authenticationSystem.dto.Request;
import com.practicas.authenticationSystem.dto.Response;
import com.practicas.authenticationSystem.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class UserController {

	private UserService userService;
	
	@PostMapping("signUp")
	public ResponseEntity<Response> signUp(@RequestBody Request request){
		return userService.signup(request);
	}
} 
