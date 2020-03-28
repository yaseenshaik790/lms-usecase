package com.leavedemo.leavemanagementsystem.dto;

/**
 * ErrorResponse class is used to send method level constraint validation message to the user
 * @author YaseenShaik
 */
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("statusCode")
	private Integer statusCode;

	@JsonProperty("errorList")
	private List<String> errorList;

	public ErrorResponse(String errorMessage, Integer statusCode) {
		super();
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}

	public ErrorResponse(String errorMessage, List<String> errorList, Integer statusCode) {
		this.errorMessage = errorMessage;
		this.errorList = errorList;
		this.statusCode = statusCode;
	}

}
