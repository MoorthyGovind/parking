package com.hcl.parking.util;

import com.hcl.parking.dto.ViewAvailableParkingDto;
import com.hcl.parking.entity.ParkingSlotAvailable;

public class ConverterUtil {

	private ConverterUtil() {
	}
	
	/**
	 * convertCourseDto -> entity values set to the dto model
	 * @param course
	 * @return
	 */
	public static ViewAvailableParkingDto convertAvailableParkingDto(ParkingSlotAvailable parkingSlotAvailable) {
		ViewAvailableParkingDto viewAvailableParkingDto = new ViewAvailableParkingDto();
		viewAvailableParkingDto.setSlotAvailableId(parkingSlotAvailable.getId());
		viewAvailableParkingDto.setCampusLocation(parkingSlotAvailable.getUserSlotId().getParkingSlot().getCampusLocation());
		viewAvailableParkingDto.setBlockName(parkingSlotAvailable.getUserSlotId().getParkingSlot().getBlockName());
		viewAvailableParkingDto.setFloorNo(parkingSlotAvailable.getUserSlotId().getParkingSlot().getFloorNo());
		viewAvailableParkingDto.setAvailableDate(parkingSlotAvailable.getBookingDate());
		viewAvailableParkingDto.setSlotNo(parkingSlotAvailable.getUserSlotId().getParkingSlot().getSlotNo());
		viewAvailableParkingDto.setSlotLocation(parkingSlotAvailable.getUserSlotId().getParkingSlot().getSlotLocation());
		viewAvailableParkingDto.setSlotOwnerName(parkingSlotAvailable.getUserSlotId().getUserId().getName());
		return viewAvailableParkingDto;
	}
}
