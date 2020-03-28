package com.leavedemo.leavemanagementsystem.service;

import java.util.List;

/**
 * interface for UserService
 * @author ShaikYaseen
 */
import com.leavedemo.leavemanagementsystem.dto.LeaveDTO;
import com.leavedemo.leavemanagementsystem.dto.LeaveRequest;
import com.leavedemo.leavemanagementsystem.response.LeaveResponse;

public interface UserService {

	public List<LeaveDTO> getLeavsByUserId(Long userId);

	public LeaveResponse applyLeave(Long userId, LeaveRequest leaveRequest);

}
