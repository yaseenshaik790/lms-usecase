package com.leavedemo.leavemanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leavedemo.leavemanagementsystem.dto.LeaveDTO;
import com.leavedemo.leavemanagementsystem.dto.LeaveRequest;
import com.leavedemo.leavemanagementsystem.exception.InvalidFromDateException;
import com.leavedemo.leavemanagementsystem.exception.InvalidToDateException;
import com.leavedemo.leavemanagementsystem.exception.LeaveIdNotAvailableException;
import com.leavedemo.leavemanagementsystem.exception.LeavesNotAvailableException;
import com.leavedemo.leavemanagementsystem.exception.UserNotFoundException;
import com.leavedemo.leavemanagementsystem.response.LeaveResponse;
import com.leavedemo.leavemanagementsystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * @return Leave details
	 * @throws UserNotFoundException       when user not exist
	 * @throws LeavesNotAvailableException when leaves not available
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<List<LeaveDTO>> getLeavsByUserId(@PathVariable Long userId) {

		logger.info("Fetching the user leave details");

		return new ResponseEntity<>(userService.getLeavsByUserId(userId), HttpStatus.OK);
	}

	/**
	 * @return leave approved message with status code
	 * 
	 * @throws UserNotFoundException        when user not exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws LeaveIdNotAvailableException when leave id doesn't exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws InvalidToDateException       when client enters invalid To date
	 * @throws InvalidFromDateException     when client enters invalid To date
	 */
	@PostMapping("/{userId}")
	public ResponseEntity<LeaveResponse> applyLeave(@PathVariable Long userId,
			@Valid @RequestBody LeaveRequest leaveRequest) {

		logger.info("User apply for an leave");

		return new ResponseEntity<>(userService.applyLeave(userId, leaveRequest), HttpStatus.OK);
	}

}
