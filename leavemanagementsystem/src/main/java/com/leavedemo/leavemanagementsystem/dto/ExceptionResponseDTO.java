package com.leavedemo.leavemanagementsystem.dto;

/**
 * ExceptionResponseDTO class is used to send exception message with status code when exception occurs
 * @author ShaikYaseen
 */
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionResponseDTO {
	@JsonProperty("message")
	private String message;
	@JsonProperty("errorCode")
	private Integer errorCode;

	public ExceptionResponseDTO(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
