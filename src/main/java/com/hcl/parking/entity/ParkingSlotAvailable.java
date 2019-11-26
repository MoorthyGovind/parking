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
public class ParkingSlotAvailable extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_slot_id")
	private UserParkingSlot userSlotId;

	@Column(name="booking_date")
	private LocalDate bookingDate;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="temp_parking_user_id")
	private User tempParkingUserId;

	@Column(name="booking_status")
	private String bookingStatus;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserParkingSlot getUserSlotId() {
		return userSlotId;
	}

	public void setUserSlotId(UserParkingSlot userSlotId) {
		this.userSlotId = userSlotId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public User getTempParkingUserId() {
		return tempParkingUserId;
	}

	public void setTempParkingUserId(User tempParkingUserId) {
		this.tempParkingUserId = tempParkingUserId;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
}
