package com.leavedemo.leavemanagementsystem.exception;

/**
 * Exception to be throw when the user not eligible for taking leave.
 * 
 * @author ShaikYaseen
 *
 */

public class LeavesNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 609;

	public LeavesNotAvailableException(Long noOfDays) {
		super("User is not eligible for taking a leave : " + noOfDays);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
