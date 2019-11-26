package com.hcl.parking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RequestSlotDto {

	@NotBlank (message = "userId is mandatory")
	@Email (message = "Enter valid email address")
	private String userId;
	
	@NotBlank (message = "please select booking date range")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}", message="Invalid request from date")
	private String requestFrom;
	
	@NotBlank (message = "please select booking date range")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}", message="Invalid request to date")
	private String requestTo;
	
	private String remarks;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public String getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(String requestTo) {
		this.requestTo = requestTo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
