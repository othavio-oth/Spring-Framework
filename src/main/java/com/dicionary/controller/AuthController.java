package com.dicionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicionary.dto.auth.AuthForm;
import com.dicionary.dto.auth.TokenDTO;
import com.dicionary.service.AuthenticationService;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping
	public ResponseEntity<TokenDTO> auth(@RequestBody AuthForm authForm){
		try {
			return ResponseEntity.ok(authenticationService.authenticate(authForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
