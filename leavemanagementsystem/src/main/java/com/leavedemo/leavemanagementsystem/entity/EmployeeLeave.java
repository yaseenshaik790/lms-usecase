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

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_leave")
@Getter
@Setter
@Data
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

	public EmployeeLeave() {
	}

}
