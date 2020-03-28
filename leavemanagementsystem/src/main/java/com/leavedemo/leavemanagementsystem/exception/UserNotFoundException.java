package com.leavedemo.leavemanagementsystem.exception;

/**
 * Exception to be throw when the user with provided user Id does not exists.
 * 
 * @author ShaikYaseen
 *
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 602;

	public UserNotFoundException(Long userId) {
		super("User not found with an id: " + userId);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
