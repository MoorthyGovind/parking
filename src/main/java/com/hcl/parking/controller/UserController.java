package com.hcl.parking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parking.common.AppConstants;
import com.hcl.parking.dto.UserDto;
import com.hcl.parking.entity.User;
import com.hcl.parking.response.ApiResponse;
import com.hcl.parking.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	/**
	 * login checks with userId and password
	 * @param userDto
	 * @return
	 */
	@PostMapping(value="/login")
	public ResponseEntity<ApiResponse> login(@Valid @RequestBody UserDto userDto){
		logger.info("user login checks...");
		ApiResponse response;
		User user = userService.login(userDto);
		Optional<User> isUser = Optional.ofNullable(user);
		if(isUser.isPresent()) {
			 response = new ApiResponse(AppConstants.SUCCESS, HttpStatus.OK.value(), 
					AppConstants.LOGIN_SUCCESSFULLY);
		}else {
			response = new ApiResponse(AppConstants.FAILURE, HttpStatus.NOT_FOUND.value(), 
					AppConstants.INVALID_LOGIN);
		}
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
