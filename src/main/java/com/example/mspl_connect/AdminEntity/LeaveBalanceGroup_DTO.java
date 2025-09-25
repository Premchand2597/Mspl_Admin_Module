package com.example.mspl_connect.AdminEntity;

import java.util.List;
import java.util.Map;

public class LeaveBalanceGroup_DTO {

	private List<String> clickedLeaveType;
    private Map<String, Double> validLeaveBalance;
	
    public List<String> getClickedLeaveType() {
		return clickedLeaveType;
	}
	public void setClickedLeaveType(List<String> clickedLeaveType) {
		this.clickedLeaveType = clickedLeaveType;
	}
	public Map<String, Double> getValidLeaveBalance() {
		return validLeaveBalance;
	}
	public void setValidLeaveBalance(Map<String, Double> validLeaveBalance) {
		this.validLeaveBalance = validLeaveBalance;
	}
	@Override
	public String toString() {
		return "LeaveBalanceGroup_DTO [clickedLeaveType=" + clickedLeaveType + ", validLeaveBalance="
				+ validLeaveBalance + "]";
	}
    
}
