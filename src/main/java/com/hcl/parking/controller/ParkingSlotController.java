package com.hcl.parking.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parking.common.AppConstants;
import com.hcl.parking.dto.AvailableSlotDto;
import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.ViewAvailableParkingDto;
import com.hcl.parking.response.ApiResponse;
import com.hcl.parking.service.ParkingSlotService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/slots")
public class ParkingSlotController {
	private static final Logger logger = LoggerFactory.getLogger(ParkingSlotController.class);

	@Autowired
	ParkingSlotService parkingSlotService;
	
	/**
	 * createAvailableSlot
	 * @param availableSlotDto
	 * @return
	 * @throws NotFoundException
	 */
	@PostMapping("")
	public ResponseEntity<ApiResponse> createAvailableSlot(@Valid @RequestBody AvailableSlotDto availableSlotDto) 
			throws NotFoundException{
		logger.info("createAvailableSlot...");

		ApiResponse apiResponse = parkingSlotService.createAvailableSlot(availableSlotDto);
		Optional<String> isReponse = Optional.ofNullable(apiResponse.getStatus());
		if(isReponse.isPresent()) {
			apiResponse.setStatusCode(HttpStatus.OK.value());
		}else {
			apiResponse.setStatus(AppConstants.FAILURE);
			apiResponse.setMessage(AppConstants.SLOT_ERROR_MESSAGE);
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<ViewAvailableParkingDto>> findAllAvailableSlots(){
		List<ViewAvailableParkingDto> parkingLists = parkingSlotService.findAllAvailableSlots();
		return new ResponseEntity<>(parkingLists, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param requestSlotDto
	 * @return
	 * @throws NotFoundException
	 * @throws ParseException
	 */
	@PostMapping("/request")
	public ResponseEntity<ApiResponse> createParkingRequest(@Valid @RequestBody RequestSlotDto requestSlotDto) 
			throws NotFoundException, ParseException{
		logger.info("createParkingRequest...");
		ApiResponse apiResponse = parkingSlotService.createParkingRequest(requestSlotDto);
		Optional<String> isReponse = Optional.ofNullable(apiResponse.getStatus());
		if(isReponse.isPresent()) {
			apiResponse.setStatusCode(HttpStatus.OK.value());
		}else {
			apiResponse.setStatus(AppConstants.FAILURE);
			apiResponse.setMessage("");
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	
}
