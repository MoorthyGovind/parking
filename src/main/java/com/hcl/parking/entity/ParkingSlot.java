package com.hcl.parking.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ParkingSlot{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="campus_location")
	private String campusLocation;

	@Column(name="block_name")
	private String blockName;
	
	@Column(name="floor_no")
	private String floorNo;
	
	@Column(name="slot_no")
	private String slotNo;
	
	@Column(name="slot_location")
	private String slotLocation;

	@OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "parkingSlot")
    private Set<UserParkingSlot> userParkingSlot;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<UserParkingSlot> getUserParkingSlot() {
		return userParkingSlot;
	}

	public void setUserParkingSlot(Set<UserParkingSlot> userParkingSlot) {
		this.userParkingSlot = userParkingSlot;
	}

}
