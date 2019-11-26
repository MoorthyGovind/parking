package com.hcl.parking.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;

	@Column(name="user_id")
	private String userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="sap_id")
	private String sapId;
	
	@Column(name="name")
	private String name;

	@Column(name="role_type")
	private String roleType;
	
	@Column(name="is_parking_slot")
	private String isParkingSlot;
	
	@Column(name="status")
	private Boolean status;

	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "userId")
    private UserParkingSlot userParkingSlot;
	
	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "tempParkingUserId")
    private ParkingSlotAvailable parkingSlotAvailable;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSapId() {
		return sapId;
	}

	public void setSapId(String sapId) {
		this.sapId = sapId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getIsParkingSlot() {
		return isParkingSlot;
	}

	public void setIsParkingSlot(String isParkingSlot) {
		this.isParkingSlot = isParkingSlot;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public UserParkingSlot getUserParkingSlot() {
		return userParkingSlot;
	}

	public void setUserParkingSlot(UserParkingSlot userParkingSlot) {
		this.userParkingSlot = userParkingSlot;
	}
	
	public ParkingSlotAvailable getParkingSlotAvailable() {
		return parkingSlotAvailable;
	}

	public void setParkingSlotAvailable(ParkingSlotAvailable parkingSlotAvailable) {
		this.parkingSlotAvailable = parkingSlotAvailable;
	}
}
