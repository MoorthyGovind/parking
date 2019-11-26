package com.hcl.parking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parking.entity.ParkingSlotRequest;

@Repository
public interface ParkingSlotRequestRepository extends JpaRepository<ParkingSlotRequest, Long>{

	
	List<ParkingSlotRequest> findAllByRequestFromGreaterThanEqual(LocalDate startDate);
}
