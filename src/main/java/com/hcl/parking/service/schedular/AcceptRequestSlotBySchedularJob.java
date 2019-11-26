package com.hcl.parking.service.schedular;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.hcl.parking.common.BookingStatus;
import com.hcl.parking.entity.ParkingSlotAvailable;
import com.hcl.parking.entity.ParkingSlotRequest;
import com.hcl.parking.repository.ParkingSlotAvailableRepository;
import com.hcl.parking.repository.ParkingSlotRequestRepository;

@Configuration
@EnableScheduling
public class AcceptRequestSlotBySchedularJob {
	private static final Logger logger = LoggerFactory.getLogger(AcceptRequestSlotBySchedularJob.class);

	@Autowired
	ParkingSlotRequestRepository parkingSlotRequestRepository;
	
	@Autowired
	ParkingSlotAvailableRepository parkingSlotAvailableRepository;
	
	/**
	 * executeTask
	 */
	@Scheduled(cron = "0 0/1 * * * *")
    public void executeTask() {
		logger.info("allocatedRequestSlotsBySchedularJob cron executed.........");
	      LocalDate currentDate = LocalDate.now();
       List<ParkingSlotRequest> parkingSlotRequest = parkingSlotRequestRepository.
    		   findAllByRequestFromGreaterThanEqual(currentDate);
       parkingSlotRequest.forEach(request -> {
    	   long noOfDaysBetween = ChronoUnit.DAYS.between(request.getRequestFrom(), request.getRequestTo());
    	   if(noOfDaysBetween > 0) {
    		   IntStream intStream = IntStream.range(1, (int)noOfDaysBetween+1);
    		   intStream.forEach(value->{
    			   List<ParkingSlotAvailable> availableSlots = parkingSlotAvailableRepository.
    	    		   		findAllByBookingDateAndTempParkingUserIdIsNull(request.getRequestFrom());
    			   availableSlots.forEach(availableSlot ->{
        			   availableSlot.setTempParkingUserId(request.getRequestUserId());
        			   availableSlot.setBookingStatus(BookingStatus.BOOKED.getStatus());
        			   parkingSlotAvailableRepository.save(availableSlot);
        		   });
    		   });
               
    	   }else {
    		   List<ParkingSlotAvailable> availableSlots = parkingSlotAvailableRepository.
    		   		findAllByBookingDateAndTempParkingUserIdIsNull(request.getRequestFrom());
    		   availableSlots.forEach(availableSlot ->{
    			   availableSlot.setTempParkingUserId(request.getRequestUserId());
    			   availableSlot.setBookingStatus(BookingStatus.BOOKED.getStatus());
    			   parkingSlotAvailableRepository.save(availableSlot);
    		   });
    	   }
       });
		
    }
}
