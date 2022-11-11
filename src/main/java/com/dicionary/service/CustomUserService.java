package com.dicionary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dicionary.model.User;
import com.dicionary.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByEmail(username);
	        if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return user;
	}

	public User getUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new RuntimeException( "User not found");
		}
		return user.get();
		
	}
	
	public User saveUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}

}
