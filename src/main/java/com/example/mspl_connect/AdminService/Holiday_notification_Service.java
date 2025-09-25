package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.Holiday_notification_Entity;
import com.example.mspl_connect.AdminRepo.Holiday_notification_Repo;


@Service
public class Holiday_notification_Service {

	@Autowired
	private Holiday_notification_Repo holiday_notification_Repo;
	
	public boolean isEmpIdExistsInTheHolidayNotificationTable(String emp_id) {
		String empIdExists = holiday_notification_Repo.isEmpIdPresentInTheHolidayNotificationTable(emp_id);
		if(empIdExists == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public Holiday_notification_Entity getDataBasedOnEmpId(String emp_id) {
		return holiday_notification_Repo.fetchDataBasedOnEmpId(emp_id);
	}
	
	public void editHolidayNotificationFlagValueByEmpId(String emp_id, String flag, long holidayCount, String holidayYear) {
		holiday_notification_Repo.updateHolidayNotificationFlagValueByEmpId(emp_id, flag, holidayCount, holidayYear);
	}
	
	public void insertNewHolidayNotificationData(Holiday_notification_Entity holiday_notification_Entity) {
		holiday_notification_Repo.save(holiday_notification_Entity);
	}
	
}
