package com.hcl.parking.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ViewAvailableParkingDto {

	private Long slotAvailableId;
	private String campusLocation;
	private String blockName;
	private String floorNo;
	private String slotNo;
	private String slotLocation;
	private String slotOwnerName;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate availableDate;
	
	public Long getSlotAvailableId() {
		return slotAvailableId;
	}

	public void setSlotAvailableId(Long slotAvailableId) {
		this.slotAvailableId = slotAvailableId;
	}
	
	public String getCampusLocation() {
		return campusLocation;
	}

	public void setCampusLocation(String campusLocation) {
		this.campusLocation = campusLocation;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}


	public String getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(String slotNo) {
		this.slotNo = slotNo;
	}

	public String getSlotLocation() {
		return slotLocation;
	}

	public void setSlotLocation(String slotLocation) {
		this.slotLocation = slotLocation;
	}

	public String getSlotOwnerName() {
		return slotOwnerName;
	}

	public void setSlotOwnerName(String slotOwnerName) {
		this.slotOwnerName = slotOwnerName;
	}

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}
}
