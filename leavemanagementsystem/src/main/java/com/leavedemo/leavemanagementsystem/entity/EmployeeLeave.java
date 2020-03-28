package com.leavedemo.leavemanagementsystem.entity;

/**
 * EmployeeLeave is used to save employee updated leaves
 * @author ShaikYaseen
 */
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_leave")
public class EmployeeLeave implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_leave_id")
	private Long employeeLeaveId;

	@Column(name = "leave_reason")
	private String leaveReason;

	@Column(name = "leave_from_date")
	private LocalDate leaveFfromDate;

	@Column(name = "leave_to_date")
	private LocalDate leaveToDate;

	@Column(name = "no_of_days")
	private Long noOfDays;

	@Column(name = "user_id")
	private Long userId;

	public EmployeeLeave(String leaveReason, LocalDate leaveFfromDate, LocalDate leaveToDate, Long noOfDays,
			Long userId) {
		super();

		this.leaveReason = leaveReason;
		this.leaveFfromDate = leaveFfromDate;
		this.leaveToDate = leaveToDate;
		this.noOfDays = noOfDays;
		this.userId = userId;
	}

	public Long getEmployeeLeaveId() {
		return employeeLeaveId;
	}

	public void setEmployeeLeaveId(Long employeeLeaveId) {
		this.employeeLeaveId = employeeLeaveId;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public LocalDate getLeaveFfromDate() {
		return leaveFfromDate;
	}

	public void setLeaveFfromDate(LocalDate leaveFfromDate) {
		this.leaveFfromDate = leaveFfromDate;
	}

	public LocalDate getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(LocalDate leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	public Long getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}

	public EmployeeLeave() {
	}

}
