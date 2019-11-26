package com.hcl.parking.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ParkingSlotRequest extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="request_user_id")
	private User requestUserId;

	@Column(name="request_from")
	private LocalDate requestFrom;
	
	@Column(name="request_to")
	private LocalDate requestTo;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(User requestUserId) {
		this.requestUserId = requestUserId;
	}

	public LocalDate getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(LocalDate requestFrom) {
		this.requestFrom = requestFrom;
	}

	public LocalDate getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(LocalDate requestTo) {
		this.requestTo = requestTo;
	}
}
