package com.hcl.parking.common;

public enum BookingStatus {
	
	PENDING("Pending"),
	BOOKED("Booked"),
	REJECTED("Rejected");

	private String status;
	
	BookingStatus(String parkingStatus){
		this.status = parkingStatus;
	}
	
	public String getStatus() {
		return status;
	}
	
}
