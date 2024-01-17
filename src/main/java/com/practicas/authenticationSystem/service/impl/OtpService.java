package com.practicas.authenticationSystem.service.impl;

import java.time.LocalDateTime;


import org.springframework.stereotype.Service;

import com.practicas.authenticationSystem.dto.EmailDetails;
import com.practicas.authenticationSystem.dto.OtpRequest;
import com.practicas.authenticationSystem.dto.OtpResponse;
import com.practicas.authenticationSystem.dto.OtpValidationRequest;
import com.practicas.authenticationSystem.dto.Response;
import com.practicas.authenticationSystem.entity.Otp;
import com.practicas.authenticationSystem.repository.OtpRepository;
import com.practicas.authenticationSystem.utils.AppUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j

public class OtpService {
	
	private final OtpRepository otpRepository;
	private final EmailService emailService;
	
	
	public Response sendOtp(OtpRequest otpRequest) {
		//genaerate the otp
		//send the otp
		//save the otp
		//check if he has an otp on our system
		
		Otp existingOtp = otpRepository.findByEmail(otpRequest.getEmail());
		if(existingOtp != null) {
			otpRepository.delete(existingOtp);
		}
		String otp= AppUtils.generateOtp();
		log.info("Otp: {}", otp);
		otpRepository.save(Otp.builder()
				.email(otpRequest.getEmail())
				.otp(otp)
				.expiresAt(LocalDateTime.now().plusMinutes(2))
				.build());
		
		
		emailService.sendEmail(EmailDetails.builder()
				.subject("Do NOT DISCLOSE !!!")
				.recipient(otpRequest.getEmail())
				.messageBody("This organization has sent you an otp. This otp expired in2 minutes." + otp)
				.build());
		
		return Response.builder()
				.statusCode(200)
				.responseMessage("SUCCESS")
				.build();
	}
	
	/**
	 * ascertain that the user actually sent an otp
	 * ascertain the otp hasn´t expired 
	 * ascertain the otp is correct
	 */
	
	public Response validateOtp(OtpValidationRequest otpValidationRequest) {
		
		Otp otp= otpRepository.findByEmail(otpValidationRequest.getEmail());
		log.info("Email:{}", otpValidationRequest.getEmail());
		if(otp == null) {
			return Response.builder()
					.statusCode(400)
					.responseMessage("You  have not send an otp")
					
					.build();
		}
		if(otp.getExpiresAt().isBefore(LocalDateTime.now())) {
			return Response.builder()
					.statusCode(400)
					.responseMessage("Expired Otp")
					.build();
		}
		if(otp.getOtp().equals(otpValidationRequest.getOtp())) {
			return Response.builder()
					.statusCode(400)
					.responseMessage("Invalid Otp")
					.build();
		}
		return Response.builder()
				.statusCode(200)
				.responseMessage("SUCCESS")
				.otpResponse(OtpResponse.builder()
						.isOtpValid(true)
						.build())
				.build();
	}
	

}
