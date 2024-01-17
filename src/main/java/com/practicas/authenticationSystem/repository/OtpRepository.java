package com.practicas.authenticationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.authenticationSystem.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long>{

	Otp findByEmail(String email);
}
