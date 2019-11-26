package com.hcl.parking.service;

import java.text.ParseException;
import java.util.List;

import com.hcl.parking.dto.AvailableSlotDto;
import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.ViewAvailableParkingDto;
import com.hcl.parking.response.ApiResponse;

import javassist.NotFoundException;

public interface ParkingSlotService {

	public ApiResponse createAvailableSlot(AvailableSlotDto availableSlotDto) throws NotFoundException;
	
	public List<ViewAvailableParkingDto> findAllAvailableSlots();

	public ApiResponse createParkingRequest(RequestSlotDto requestSlotDto) throws NotFoundException, ParseException;
}
