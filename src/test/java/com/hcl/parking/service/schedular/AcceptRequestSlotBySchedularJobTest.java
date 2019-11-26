package com.hcl.parking.service.schedular;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.parking.entity.ParkingSlotAvailable;
import com.hcl.parking.entity.ParkingSlotRequest;
import com.hcl.parking.entity.User;
import com.hcl.parking.repository.ParkingSlotAvailableRepository;
import com.hcl.parking.repository.ParkingSlotRequestRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class AcceptRequestSlotBySchedularJobTest {

	@InjectMocks
	AcceptRequestSlotBySchedularJob allocatedRequestSlotsBySchedularJob;
	
	@Mock
	ParkingSlotRequestRepository parkingSlotRequestRepository;
	
	@Mock
	ParkingSlotAvailableRepository parkingSlotAvailableRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testExecuteTask() {
		 User user = new User();
		 user.setId(1L);
		 
		 List<ParkingSlotRequest> parkingSlotRequests = new ArrayList<>();
		 ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest();
		 parkingSlotRequest.setRequestFrom(LocalDate.of(2019, 11, 25));
		 parkingSlotRequest.setRequestTo(LocalDate.of(2019, 11, 25));
		 parkingSlotRequest.setRequestUserId(user);
		 
		 List<ParkingSlotAvailable> parkingSlotAvailables = new ArrayList<>();
		 ParkingSlotAvailable slotAvailable = new ParkingSlotAvailable();
		 slotAvailable.setBookingDate(LocalDate.of(2019, 11, 25));
		 parkingSlotAvailables.add(slotAvailable);
		 
		 parkingSlotRequests.add(parkingSlotRequest);
		 
	    when(parkingSlotRequestRepository.findAllByRequestFromGreaterThanEqual(LocalDate.now())).thenReturn(parkingSlotRequests);
        when(parkingSlotAvailableRepository.
        		findAllByBookingDateAndTempParkingUserIdIsNull(LocalDate.of(2019, 11, 25))).thenReturn(parkingSlotAvailables);
	    
		allocatedRequestSlotsBySchedularJob.executeTask();
	}
	
	@Test
	public void testExecuteTaskWithRange() {
		 User user = new User();
		 user.setId(1L);
		 
		 List<ParkingSlotRequest> parkingSlotRequests = new ArrayList<>();
		 ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest();
		 parkingSlotRequest.setRequestFrom(LocalDate.of(2019, 11, 23));
		 parkingSlotRequest.setRequestTo(LocalDate.of(2019, 11, 25));
		 parkingSlotRequest.setRequestUserId(user);
		 
		 List<ParkingSlotAvailable> parkingSlotAvailables = new ArrayList<>();
		 ParkingSlotAvailable slotAvailable = new ParkingSlotAvailable();
		 slotAvailable.setBookingDate(LocalDate.of(2019, 11, 23));
		 parkingSlotAvailables.add(slotAvailable);
		 
		 parkingSlotRequests.add(parkingSlotRequest);
		 
	    when(parkingSlotRequestRepository.findAllByRequestFromGreaterThanEqual(LocalDate.now())).thenReturn(parkingSlotRequests);
        when(parkingSlotAvailableRepository.
        		findAllByBookingDateAndTempParkingUserIdIsNull(LocalDate.of(2019, 11, 23))).thenReturn(parkingSlotAvailables);
	    
		allocatedRequestSlotsBySchedularJob.executeTask();
	}
}
