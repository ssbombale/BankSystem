package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	UserRepository customerRepo;
	@Override
	public Integer saveUser(User user) {
		User savedUser = customerRepo.save(user);
		return  savedUser.getId();
	}
	
//	@Override
//	public Integer saveUser(User user) {
//		User savedUser = customerRepo.save(user);
//		return  savedUser.getId();
//	}
//	

	@Override
	public User loadUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
