package com.hcl.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.parking.dto.UserDto;
import com.hcl.parking.entity.ParkingSlotAvailable;
import com.hcl.parking.entity.User;
import com.hcl.parking.entity.UserParkingSlot;
import com.hcl.parking.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	User user = new User();

	@Before
	public void init() {
		user.setId(1L);
		user.setUserId("moorthy127@gmail.com");
		user.setPassword("start@123");
		user.setSapId("51839688");
		user.setName("Moorthy");
		user.setRoleType("E1");
		user.setIsParkingSlot("1");
		user.setStatus(true);
		user.setParkingSlotAvailable(new ParkingSlotAvailable());
		user.setUserParkingSlot(new UserParkingSlot());
	}
	
	@Test
	public void testLogin() {
		UserDto userDto = new UserDto();
		userDto.setUserId("moorthy127@gmail.com");
		userDto.setPassword("start@123");
		
		when(userRepository.findByUserIdAndPassword("moorthy127@gmail.com", "start@123")).thenReturn(user);
		User userDetail = userServiceImpl.login(userDto);
		assertEquals(userDetail.getUserId(), user.getUserId());
		assertEquals(userDetail.getPassword(), user.getPassword());
		assertEquals(userDetail.getStatus(), user.getStatus());
		assertEquals(userDetail.getId(), user.getId());
		assertEquals(userDetail.getName(), user.getName());
		assertEquals(userDetail.getSapId(), user.getSapId());
		assertEquals(userDetail.getRoleType(), user.getRoleType());
		assertEquals(userDetail.getIsParkingSlot(), user.getIsParkingSlot());
		assertEquals(userDetail.getParkingSlotAvailable(), user.getParkingSlotAvailable());
		assertEquals(userDetail.getUserParkingSlot(), user.getUserParkingSlot());
	}
}
