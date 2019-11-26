package com.hcl.parking.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.parking.common.AppConstants;
import com.hcl.parking.dto.AvailableSlotDto;
import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.ViewAvailableParkingDto;
import com.hcl.parking.entity.ParkingSlot;
import com.hcl.parking.entity.ParkingSlotAvailable;
import com.hcl.parking.entity.ParkingSlotRequest;
import com.hcl.parking.entity.User;
import com.hcl.parking.entity.UserParkingSlot;
import com.hcl.parking.repository.ParkingSlotAvailableRepository;
import com.hcl.parking.repository.ParkingSlotRequestRepository;
import com.hcl.parking.repository.UserRepository;
import com.hcl.parking.response.ApiResponse;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingSlotServiceImplTest {

	@InjectMocks
	ParkingSlotServiceImpl parkingSlotServiceImpl;
	
	@Mock
	UserRepository userRepository;

	@Mock
	ParkingSlotAvailableRepository parkingSlotAvailableRepository;
	
	@Mock
	ParkingSlotRequestRepository parkingSlotRequestRepository;
	
	User user = new User();
	AvailableSlotDto availableSlotDto= new AvailableSlotDto();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		availableSlotDto.setAvailableLimit(4);
		availableSlotDto.setRemarks("Released my slots");
		availableSlotDto.setUserId("moorthy127@hcl.com");
		
		user.setId(1L);
		user.setUserId("moorthy127@hcl.com");
	}
	
	@Test
	public void testCreateAvailableSlot() throws NotFoundException {
		ApiResponse apiResponse = new ApiResponse(AppConstants.SUCCESS, HttpStatus.OK.value(), 
    			AppConstants.RECORD_CREATED_SUCCESSFULLY);
		
		when(userRepository.findByUserId(availableSlotDto.getUserId())).thenReturn(user);
		when(parkingSlotAvailableRepository.save(new ParkingSlotAvailable())).thenReturn(new ParkingSlotAvailable());
		
		apiResponse = parkingSlotServiceImpl.createAvailableSlot(availableSlotDto);
		assertEquals(AppConstants.SUCCESS, apiResponse.getStatus());
	}
	
	@Test
	public void testCreateParkingRequest() throws NotFoundException, ParseException {
		RequestSlotDto requestSlotDto= new RequestSlotDto();
		requestSlotDto.setRequestFrom("19-11-2019");
    	requestSlotDto.setRequestTo("25-11-2019");
    	requestSlotDto.setUserId("abc@hcl.com");
    	requestSlotDto.setRemarks("Requested");
    	
    	user.setId(3L);
    	user.setUserId("abu@hcl.com");
    	
		ApiResponse apiResponse = new ApiResponse(AppConstants.SUCCESS, HttpStatus.OK.value(), 
    			AppConstants.RECORD_CREATED_SUCCESSFULLY);
		
		when(userRepository.findByUserId(requestSlotDto.getUserId())).thenReturn(user);
		when(parkingSlotRequestRepository.save(new ParkingSlotRequest())).thenReturn(new ParkingSlotRequest());
		
		apiResponse = parkingSlotServiceImpl.createParkingRequest(requestSlotDto);
		assertEquals(AppConstants.SUCCESS, apiResponse.getStatus());
	}
	
	@Test
	public void testFindAllAvailableSlots() {
		
		List<ParkingSlotAvailable> parkingSlotAvailables = new ArrayList<>();
		ParkingSlotAvailable parkingSlotAvailable = new ParkingSlotAvailable();
		parkingSlotAvailable.setBookingDate(LocalDate.now());
		parkingSlotAvailable.setBookingStatus("pending");
		
		parkingSlotAvailable.setId(1L);
		
		ParkingSlot parkingSlot = new ParkingSlot();
		parkingSlot.setId(1L);
		parkingSlot.setFloorNo("B1");
		parkingSlot.setCampusLocation("Surya Surphire - Bangalore");
		parkingSlot.setBlockName("Tower 1");
		parkingSlot.setSlotLocation("Basement1");
		parkingSlot.setSlotNo("S-0001");
		
		UserParkingSlot userParkingSlot = new UserParkingSlot();
		userParkingSlot.setId(1L);
		userParkingSlot.setParkingSlot(parkingSlot);
		userParkingSlot.setUserId(user);
		userParkingSlot.setRemarks("Remarks");
		
		parkingSlotAvailable.setUserSlotId(userParkingSlot);
		
		parkingSlotAvailables.add(parkingSlotAvailable);
		
		List<ViewAvailableParkingDto> viewAvailableParkingDtos = new ArrayList<>();
		ViewAvailableParkingDto viewAvailableParkingDto = new ViewAvailableParkingDto();
		viewAvailableParkingDto.setAvailableDate(LocalDate.now());
		viewAvailableParkingDtos.add(viewAvailableParkingDto);
		
		when(parkingSlotAvailableRepository.findAllByTempParkingUserIdIsNull()).thenReturn(parkingSlotAvailables);
		
		viewAvailableParkingDtos = parkingSlotServiceImpl.findAllAvailableSlots();
		assertThat(viewAvailableParkingDtos).hasSize(1);
		viewAvailableParkingDtos.forEach(dto -> {
			assertThat(dto.getAvailableDate()).isNotNull();
			assertThat(dto.getBlockName()).isNotNull();
			assertThat(dto.getCampusLocation()).isNotNull();
			assertThat(dto.getFloorNo()).isNotNull();
			assertThat(dto.getSlotAvailableId()).isNotNull();
			assertThat(dto.getSlotLocation()).isNotNull();
			assertThat(dto.getSlotNo()).isNotNull();
		});
		
	}
	
}