package com.hcl.parking.service;

import com.hcl.parking.dto.UserDto;
import com.hcl.parking.entity.User;

@FunctionalInterface
public interface UserService {

	User login(UserDto userDto);
}
