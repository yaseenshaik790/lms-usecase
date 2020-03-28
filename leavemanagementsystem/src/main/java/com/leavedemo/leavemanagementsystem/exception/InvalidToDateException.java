package com.leavedemo.leavemanagementsystem.exception;

import java.time.LocalDate;

/**
 * Exception to be throw when the user enters past date.
 * 
 * @author ShaikYaseen
 *
 */
public class InvalidToDateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 605;

	public InvalidToDateException(LocalDate localDate) {
		super("Invalid date:  " + localDate + " ,please give future date : ");
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
