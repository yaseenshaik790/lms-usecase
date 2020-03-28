package com.leavedemo.leavemanagementsystem.exception;

/**
 * Exception to be throw when the user not eligible to take leaves.
 * 
 * @author ShaikYaseen
 *
 */

public class LeaveIdNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 605;

	public LeaveIdNotAvailableException(Long leaveId) {
		super("Not eligible with an leave id  :  " + leaveId);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}