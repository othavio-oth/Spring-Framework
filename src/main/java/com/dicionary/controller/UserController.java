package com.dicionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicionary.dto.auth.UserDTO;
import com.dicionary.model.User;
import com.dicionary.service.CustomUserService;

@RestController
@RequestMapping("v1/register")
public class UserController {

	@Autowired
	private CustomUserService customUserService;
	
	
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody UserDTO dto){
		User user = customUserService.saveUser(dto.fromDto());
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
}
