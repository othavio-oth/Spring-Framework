package com.dicionary.dto.auth;

import com.dicionary.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	String name;
	String email;
	String password;
	
	public User fromDto() {
		return new User(null,this.name,this.email,this.password);
	}
}
