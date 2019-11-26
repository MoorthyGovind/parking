package com.hcl.parking.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.parking.common.AppConstants;
import com.hcl.parking.dto.AvailableSlotDto;
import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.ViewAvailableParkingDto;
import com.hcl.parking.response.ApiResponse;
import com.hcl.parking.service.ParkingSlotService;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingSlotControllerTest {

	
	@InjectMocks
	ParkingSlotController parkingSlotController;
	
    @Mock
    ParkingSlotService parkingSlotService;
    
    @Before
    public void init() {
		MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateAvailableSlot() throws NotFoundException, ParseException{
    	AvailableSlotDto availableSlotDto = new AvailableSlotDto();
    	availableSlotDto.setAvailableLimit(1);
    	availableSlotDto.setUserId("moorthy127@hcl.com");
    	availableSlotDto.setRemarks("Release Slots");
    	
    	ApiResponse apiResponse = new ApiResponse(AppConstants.SUCCESS, HttpStatus.OK.value(), 
    			AppConstants.RECORD_CREATED_SUCCESSFULLY);
    	when(parkingSlotService.createAvailableSlot(availableSlotDto)).thenReturn(apiResponse);
    	
    	ResponseEntity<ApiResponse> response = parkingSlotController.createAvailableSlot(availableSlotDto);
    	assertEquals(AppConstants.SUCCESS, response.getBody().getStatus());
    	assertEquals(200, response.getBody().getStatusCode());
    	assertEquals(AppConstants.RECORD_CREATED_SUCCESSFULLY, response.getBody().getMessage());
    }
    
    @Test
    public void testCreateAvailableSlotUserNull() throws NotFoundException, ParseException{
    	AvailableSlotDto availableSlotDto = new AvailableSlotDto();
    	availableSlotDto.setAvailableLimit(1);
    	availableSlotDto.setUserId("moorthy@gmail.com");
    	availableSlotDto.setRemarks("Release Slots");
    	
    	ApiResponse apiResponse = new ApiResponse(null, 0, null);
    	when(parkingSlotService.createAvailableSlot(availableSlotDto)).thenReturn(apiResponse);
    	
    	ResponseEntity<ApiResponse> response = parkingSlotController.createAvailableSlot(availableSlotDto);
    	assertEquals(AppConstants.FAILURE, response.getBody().getStatus());
    	assertEquals(400, response.getBody().getStatusCode());
    }
    
    @Test
    public void testFindAllAvailableSlots() {
    	List<ViewAvailableParkingDto> viewAvailableParkingDtos = new ArrayList<>();
    	LocalDate localDate = LocalDate.now();
    	ViewAvailableParkingDto viewAvailableParkingDto = new ViewAvailableParkingDto();
    	viewAvailableParkingDto.setAvailableDate(localDate);
    	viewAvailableParkingDto.setBlockName("Tower 1");
    	viewAvailableParkingDto.setCampusLocation("Surya Surphire - Bangalore");
    	viewAvailableParkingDto.setFloorNo("B1");
    	viewAvailableParkingDto.setSlotLocation("Basement1");
    	viewAvailableParkingDtos.add(viewAvailableParkingDto);
    	
    	when(parkingSlotService.findAllAvailableSlots()).thenReturn(viewAvailableParkingDtos);
    	
    	ResponseEntity<List<ViewAvailableParkingDto>> result = parkingSlotController.findAllAvailableSlots();
    	assertThat(result.getBody()).hasSize(1);
    }
    
    @Test
    public void testCreateParkingRequest() throws NotFoundException, ParseException {
    	RequestSlotDto requestSlotDto = new RequestSlotDto();
    	requestSlotDto.setRequestFrom("19-11-2019");
    	requestSlotDto.setRequestTo("25-11-2019");
    	requestSlotDto.setUserId("abc@hcl.com");;
    	requestSlotDto.setRemarks("Requested");
    	
    	ApiResponse apiResponse = new ApiResponse(AppConstants.SUCCESS, HttpStatus.OK.value(), 
    			AppConstants.RECORD_CREATED_SUCCESSFULLY);    	
    	when(parkingSlotService.createParkingRequest(requestSlotDto)).thenReturn(apiResponse);
    	
    	ResponseEntity<ApiResponse> response = parkingSlotController.createParkingRequest(requestSlotDto);
    	assertEquals(AppConstants.SUCCESS, response.getBody().getStatus());
    	assertEquals(200, response.getBody().getStatusCode());
    	assertEquals(AppConstants.RECORD_CREATED_SUCCESSFULLY, response.getBody().getMessage());
    }
    
    @Test
    public void testCreateParkingRequestUserNull() throws NotFoundException, ParseException{
    	RequestSlotDto requestSlotDto = new RequestSlotDto();
    	requestSlotDto.setRequestFrom("19-11-2019");
    	requestSlotDto.setRequestTo("25-11-2019");
    	requestSlotDto.setUserId("abc123@hcl.com");;
    	requestSlotDto.setRemarks("Requested");
    	
    	ApiResponse apiResponse = new ApiResponse(null, 0, null);
    	when(parkingSlotService.createParkingRequest(requestSlotDto)).thenReturn(apiResponse);
    	
    	ResponseEntity<ApiResponse> response = parkingSlotController.createParkingRequest(requestSlotDto);
    	assertEquals(AppConstants.FAILURE, response.getBody().getStatus());
    	assertEquals(400, response.getBody().getStatusCode());
    }
}
