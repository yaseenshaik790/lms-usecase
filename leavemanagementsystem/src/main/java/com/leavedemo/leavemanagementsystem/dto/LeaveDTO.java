package com.leavedemo.leavemanagementsystem.dto;

public class LeaveDTO {

	private Long leaveId;

	private String leaveCategery;

	private Integer noOfDays;

	private Long userId;

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveCategery() {
		return leaveCategery;
	}

	public void setLeaveCategery(String leaveCategery) {
		this.leaveCategery = leaveCategery;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
