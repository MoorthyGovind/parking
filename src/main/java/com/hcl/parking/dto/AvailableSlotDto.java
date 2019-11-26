package com.hcl.parking.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AvailableSlotDto {

	@NotBlank (message = "userId is mandatory")
	@Email (message = "Enter valid email address")
	private String userId;
	
	@Digits(fraction = 0, integer = 10, message ="add a digit msg")
    private Integer availableLimit;
	
	private String remarks;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Integer getAvailableLimit() {
		return availableLimit;
	}
	
	public void setAvailableLimit(Integer availableLimit) {
		this.availableLimit = availableLimit;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
