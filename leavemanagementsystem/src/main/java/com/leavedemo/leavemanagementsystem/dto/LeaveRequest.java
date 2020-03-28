package com.leavedemo.leavemanagementsystem.dto;

/**
 * LeaveRequest class is used to send the leave request 
 * @author ShaikYaseen
 */
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LeaveRequest {

	@NotNull(message = "leave id is required")
	private Long leaveId;

	@NotNull(message = "leave type is required")
	private String leaveReason;

	@NotNull(message = "From date is required")
	private LocalDate fromDate;

	private LocalDate toDate;

	
}
