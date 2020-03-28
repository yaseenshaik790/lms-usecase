package com.leavedemo.leavemanagementsystem.response;

/**
 * LeaveResponse class is used to send the leave approved message with status code
 * @author ShaikYaseen
 */
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeaveResponse {

	@JsonProperty("message")
	private String message;
	@JsonProperty("statusCode")
	private Integer statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public LeaveResponse(String message, Integer statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

}
