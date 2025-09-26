package com.example.mspl_connect.AdminService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.HolidaysList;
import com.example.mspl_connect.AdminRepo.AdminHolidayRepo;

@Service
public class AdminHolidayService {
	@Autowired
	private AdminHolidayRepo adminHolidayRepo;
	
	 
    /*public List<HolidaysList> getAllHolidayList() {
        List<HolidaysList> holidayList = adminHolidayRepo.findAll();
        
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        return holidayList.stream().map(holiday -> {
            try {
                String formattedDate = targetFormat.format(originalFormat.parse(holiday.getDate()));
                holiday.setDate(formattedDate);
            } catch (Exception e) {
                // Handle parsing and formatting exceptions here
                e.printStackTrace();
            }
            return holiday;
        }).collect(Collectors.toList());
    }*/
	
	/*public List<HolidaysList> getHolidayListByYear(String year) {
		System.out.println("year------"+year);
	    List<HolidaysList> holidayList = adminHolidayRepo.findByYear(year);
	    
	    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
	    
	    return holidayList.stream().map(holiday -> {
	        try {
	            String formattedDate = targetFormat.format(originalFormat.parse(holiday.getDate()));
	            holiday.setDate(formattedDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return holiday;
	    }).collect(Collectors.toList());
	}*/
	
	public List<HolidaysList> getHolidayListByYear(String year) {
	    System.out.println("year------" + year);

	    List<HolidaysList> holidayList = adminHolidayRepo.findByYear(year);
	    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");

	    return holidayList.stream()
	        .sorted((h1, h2) -> {
	            try {
	                Date d1 = originalFormat.parse(h1.getDate());
	                Date d2 = originalFormat.parse(h2.getDate());
	                return d1.compareTo(d2);
	            } catch (Exception e) {
	                e.printStackTrace();
	                return 0;
	            }
	        })
	        .map(holiday -> {
	            try {
	                String formattedDate = targetFormat.format(originalFormat.parse(holiday.getDate()));
	                holiday.setDate(formattedDate);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return holiday;
	        })
	        .collect(Collectors.toList());
	}

	public List<Integer> getAvailableYears() {
	    return adminHolidayRepo.findDistinctYears();
	}
}
