package com.dicionary.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dicionary.model.User;
import com.dicionary.service.AuthenticationService;
import com.dicionary.service.CustomUserService;

public class AuthFilter extends OncePerRequestFilter{
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private CustomUserService customUserService;
	
	
	public AuthFilter(AuthenticationService authenticationService, CustomUserService customUserService) {
		this.authenticationService = authenticationService;
		this.customUserService = customUserService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		String token = null;
		if(header!=null&&header.startsWith("Bearer ")) {
			token = header.substring(7, header.length());
		}
		
		if(authenticationService.validateToken(token)) {
			Long userId = authenticationService.getUserId(token);
			User user = customUserService.getUserById(userId);
			SecurityContextHolder
			.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
		}
		
		filterChain.doFilter(request, response);
		
	}

}
