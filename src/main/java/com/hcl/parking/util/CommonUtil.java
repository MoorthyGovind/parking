package com.hcl.parking.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

	private CommonUtil() {
	}
	
	/**
	 * 
	 * @param date
	 * @param workdays
	 * @return
	 */
	public static List<LocalDate> calculateDaysWithWorkingDays(LocalDate date, int workdays) {
		List<LocalDate> dates = new ArrayList<>();

	    int addedDays = 0;
	    while (addedDays < workdays){
	    	date = date.plusDays(1);

	        if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY ||
	        		date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
			 	  dates.add(date);
	            ++addedDays;
	        }
	    }
	    return dates;
	}
}
