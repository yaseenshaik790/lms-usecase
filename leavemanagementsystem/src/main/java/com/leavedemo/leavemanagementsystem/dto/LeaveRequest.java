package com.leavedemo.leavemanagementsystem.dto;

/**
 * LeaveRequest class is used to send the leave request 
 * @author ShaikYaseen
 */
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class LeaveRequest {

	@NotNull(message = "leave id is required")
	private Long leaveId;

	@NotNull(message = "leave type is required")
	private String leaveReason;

	@NotNull(message = "From date is required")
	private LocalDate fromDate;

	private LocalDate toDate;

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

}
