package com.hcl.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import com.hcl.parking.entity.ParkingSlotAvailable;

@Repository
public interface ParkingSlotAvailableRepository extends JpaRepository<ParkingSlotAvailable, Long>{

	List<ParkingSlotAvailable> findAllByBookingDateAndTempParkingUserIdIsNull(LocalDate bookingDate);
	
	List<ParkingSlotAvailable> findAllByTempParkingUserIdIsNull();
}
