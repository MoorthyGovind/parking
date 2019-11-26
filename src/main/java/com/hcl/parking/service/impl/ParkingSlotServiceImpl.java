package com.hcl.parking.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.common.AppConstants;
import com.hcl.parking.common.BookingStatus;
import com.hcl.parking.dto.AvailableSlotDto;
import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.ViewAvailableParkingDto;
import com.hcl.parking.entity.ParkingSlotAvailable;
import com.hcl.parking.entity.ParkingSlotRequest;
import com.hcl.parking.entity.User;
import com.hcl.parking.repository.ParkingSlotAvailableRepository;
import com.hcl.parking.repository.ParkingSlotRequestRepository;
import com.hcl.parking.repository.UserRepository;
import com.hcl.parking.response.ApiResponse;
import com.hcl.parking.service.ParkingSlotService;
import com.hcl.parking.util.CommonUtil;
import com.hcl.parking.util.ConverterUtil;

import javassist.NotFoundException;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService{

	@Autowired
    UserRepository userRepository;
	
	@Autowired
	ParkingSlotAvailableRepository parkingSlotAvailableRepository;
	
	@Autowired
	ParkingSlotRequestRepository parkingSlotRequestRepository;
	
	/**
	 * createAvailableSlot
	 * @AvailableSlotDto availableSlotDto
	 * return ApiResponse
	 */
	@Override
	public ApiResponse createAvailableSlot(AvailableSlotDto availableSlotDto) throws NotFoundException {
		ApiResponse apiResponse = new ApiResponse();
		User user = userRepository.findByUserId(availableSlotDto.getUserId());
		Optional<User> isUser = Optional.ofNullable(user);	
		if(isUser.isPresent()) {
			//current date
		    LocalDate currentDate = LocalDate.now();
		    List<LocalDate> availableDates = CommonUtil.
		    		calculateDaysWithWorkingDays(currentDate, availableSlotDto.getAvailableLimit());
		    availableDates.forEach(date -> {
		    	ParkingSlotAvailable parkingSlotAvailable = new ParkingSlotAvailable();
		    	parkingSlotAvailable.setUserSlotId(user.getUserParkingSlot());
		    	parkingSlotAvailable.setBookingDate(date);
		    	parkingSlotAvailable.setBookingStatus(BookingStatus.PENDING.getStatus());
		    	parkingSlotAvailable.setTempParkingUserId(null);
		    	parkingSlotAvailable.setCreatedBy("Admin");
		    	parkingSlotAvailable.setCreatedDate(new Date());
		    	parkingSlotAvailable.setRemarks(availableSlotDto.getRemarks());
		    	
		    	parkingSlotAvailableRepository.save(parkingSlotAvailable);
		    });

	    	apiResponse.setMessage(AppConstants.SLOT_RELEASED_SUCCESSFULLY);
	    	apiResponse.setStatus(AppConstants.SUCCESS);
		}else {
			throw new NotFoundException("User Not Found.");
		}
		
		return apiResponse;
	}
	
	/**
	 * findAllAvailableSlots
	 */
	@Override
	public List<ViewAvailableParkingDto> findAllAvailableSlots(){
		
		List<ParkingSlotAvailable> parkingSlotAvailables = parkingSlotAvailableRepository.
				findAllByTempParkingUserIdIsNull();
		List<ViewAvailableParkingDto> viewAvailableParkingDtos = new ArrayList<>();
		parkingSlotAvailables.forEach(parkingSlotAvailable -> viewAvailableParkingDtos.
				add(ConverterUtil.convertAvailableParkingDto(parkingSlotAvailable)));
	    return viewAvailableParkingDtos;
	}
	
	/**
	 * createParkingRequest 
	 * @RequestSlotDto requestSlotDto
	 */
	@Override
	public ApiResponse createParkingRequest(RequestSlotDto requestSlotDto) throws NotFoundException, ParseException {
		ApiResponse apiResponse = new ApiResponse();
		User user = userRepository.findByUserId(requestSlotDto.getUserId());
		Optional<User> isUser = Optional.ofNullable(user);	
		if(isUser.isPresent()) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest();
			parkingSlotRequest.setRequestUserId(user);
			parkingSlotRequest.setRequestFrom(LocalDate.parse(requestSlotDto.getRequestFrom(), formatter));
			parkingSlotRequest.setRequestTo(LocalDate.parse(requestSlotDto.getRequestTo(), formatter));
			parkingSlotRequest.setRemarks(requestSlotDto.getRemarks());
			parkingSlotRequest.setCreatedDate(new Date());
			parkingSlotRequest.setCreatedBy("Admin");
			
			parkingSlotRequestRepository.save(parkingSlotRequest);
		    	
		    	apiResponse.setMessage(AppConstants.RECORD_CREATED_SUCCESSFULLY);
		    	apiResponse.setStatus(AppConstants.SUCCESS);
		}else {
			throw new NotFoundException("User Not Found.");
		}
		
		return apiResponse;
	}
}
