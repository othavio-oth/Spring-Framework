package com.dicionary.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dicionary.dto.auth.AuthForm;
import com.dicionary.dto.auth.TokenDTO;
import com.dicionary.model.User;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Value("${security.jwt.secret}")
	private String secret;
	@Value("${security.jwt.expiration}")
	private String expiration;
	@Value("${security.jwt.issuer}")
	private String issuer; 
	
	public TokenDTO authenticate(AuthForm authForm) throws AuthenticationException {
		Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getEmail(), 
				authForm.getPassword()));
		
		String token =  getToken(authenticate);
		return new TokenDTO(token);
	}

	private String getToken(Authentication authenticate) {
		User userAuthenticated = (User)authenticate.getPrincipal();
		Date now = new Date();
		Date expirationTime = new Date(now.getTime() + Long.parseLong(expiration));
		
		return JWT.create() 
				.withIssuer(issuer)
				.withExpiresAt(expirationTime)
				.withSubject(userAuthenticated.getId().toString())
				.sign(generateAlgorithm());
	}
	
	private Algorithm generateAlgorithm() {
		return Algorithm.HMAC256(secret);
	}

	
}
