package com.hcl.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.UserDto;
import com.hcl.parking.entity.User;
import com.hcl.parking.repository.UserRepository;
import com.hcl.parking.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	/**
	 * login check with userId and password
	 */
	@Override
	public User login(UserDto userDto) {
		return userRepository.findByUserIdAndPassword(userDto.getUserId(), userDto.getPassword());
	}
}
