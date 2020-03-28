package com.leavedemo.leavemanagementsystem.response;

/**
 * LeaveResponse class is used to send the leave approved message with status code
 * @author ShaikYaseen
 */
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LeaveResponse {

	@JsonProperty("message")
	private String message;
	@JsonProperty("statusCode")
	private Integer statusCode;

	public LeaveResponse(String message, Integer statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

}
