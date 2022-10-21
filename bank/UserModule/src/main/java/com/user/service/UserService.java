package com.user.service;

import org.springframework.stereotype.Service;

import com.user.entity.User;

@Service
public interface UserService {
	
	
	
	Integer saveUser (User user);

	User loadUserByUsername(String userName);
}
