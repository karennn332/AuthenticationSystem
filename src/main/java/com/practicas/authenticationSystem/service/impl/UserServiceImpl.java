package com.practicas.authenticationSystem.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practicas.authenticationSystem.dto.LoginRequest;
import com.practicas.authenticationSystem.dto.Request;
import com.practicas.authenticationSystem.dto.Response;
import com.practicas.authenticationSystem.entity.User;
import com.practicas.authenticationSystem.repository.UserRepository;
import com.practicas.authenticationSystem.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
 
	private UserRepository userRepo;
	private ModelMapper modelMaper;
	@Override
	public ResponseEntity<Response> signup(Request request) {
		// if the user exists - return error
		if(userRepo.findByEmail(request.getEmail()).isPresent()) {
			
		
		return ResponseEntity.badRequest().body(Response.builder()
				.statusCode(400)
				.responseMessage("Attempt to save duplicate user record")
				.build());
				}
		User user = User.builder()
				.email(request.getEmail())
				.password(request.getPassword())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.build();
		User savedUser = userRepo.save(user);
		
		return ResponseEntity.ok(Response.builder()
				.statusCode(200)
				.responseMessage("Success")
				.userInfo(modelMaper.map(savedUser, Request.class))
				.build());
	}

	@Override
	public ResponseEntity<Response> login(LoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response sendOtp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response validataOtp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response resetPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response changePassword() {
		// TODO Auto-generated method stub
		return null;
	}

	
	}
	
	




