package com.leavedemo.leavemanagementsystem.exception;

import java.time.LocalDate;

/**
 * Exception to be throw when the user enters past date.
 * 
 * @author ShaikYaseen
 *
 */
public class WeekendDateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 610;

	public WeekendDateException(LocalDate localDate) {
		super("Weekends " + localDate + " canot be apply leave ,please give valid date: ");
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
