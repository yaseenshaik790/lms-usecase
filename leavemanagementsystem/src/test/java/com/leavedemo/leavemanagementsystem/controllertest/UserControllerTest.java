package com.leavedemo.leavemanagementsystem.controllertest;

/**
 * UserController Test
 * @author ShaikYaseen
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.leavedemo.leavemanagementsystem.TestData;
import com.leavedemo.leavemanagementsystem.controller.UserController;
import com.leavedemo.leavemanagementsystem.dto.LeaveDTO;
import com.leavedemo.leavemanagementsystem.dto.LeaveRequest;
import com.leavedemo.leavemanagementsystem.entity.EmployeeLeave;
import com.leavedemo.leavemanagementsystem.entity.Leave;
import com.leavedemo.leavemanagementsystem.entity.User;
import com.leavedemo.leavemanagementsystem.response.LeaveResponse;
import com.leavedemo.leavemanagementsystem.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	User user, user1;
	Leave leave, leave1;
	EmployeeLeave employeeLeave, employeeLeave1;
	LeaveRequest leaveRequest;

	/**
	 * setUp() is used to saving the data before each test cases running
	 */
	@BeforeEach
	public void setUp() {

		user = new User();
		user.setEmail(TestData.email);
		user.setPassword(TestData.password);
		user.setUserName(TestData.userName);

		user1 = new User();
		user1.setEmail(TestData.email1);
		user1.setPassword(TestData.password1);
		user1.setUserName(TestData.userName1);

		leave = new Leave();
		leave.setLeaveCategery(TestData.leaveCategery);
		leave.setNoOfDays(TestData.noOfDays);

		leave1 = new Leave();
		leave1.setLeaveCategery(TestData.leaveCategery1);
		leave1.setNoOfDays(TestData.noOfDays1);

		employeeLeave = new EmployeeLeave();
		employeeLeave.setLeaveFfromDate(TestData.leavefromDate);
		employeeLeave.setLeaveToDate(TestData.leaveToDate);
		employeeLeave.setNoOfDays(TestData.noOfDay);

		leaveRequest = new LeaveRequest();
		leaveRequest.setFromDate(TestData.leavefromDate);
		leaveRequest.setToDate(TestData.leaveToDate);
		leaveRequest.setLeaveId(TestData.leaveId);

	}

	/**
	 * testing apply for an leave
	 */

	@Test
	public void testApplyLeave() {

		LeaveResponse leaveResponse = new LeaveResponse("Leave approved successfully", 666);

		when(userService.applyLeave(TestData.userId, leaveRequest)).thenReturn(leaveResponse);

		LeaveResponse leaveServiceResponse = userService.applyLeave(TestData.userId, leaveRequest);

		ResponseEntity<LeaveResponse> responseEntity = userController.applyLeave(TestData.userId, leaveRequest);

		assertThat(leaveServiceResponse.getMessage()).isEqualTo(responseEntity.getBody().getMessage());
		assertThat(leaveServiceResponse.getStatusCode()).isEqualTo(responseEntity.getBody().getStatusCode());

	}

	/**
	 * testing to apply leave for one day
	 */
	@Test
	public void testgetLeavsByUserId() {

		List<Leave> leaves = new ArrayList<>();
		leaves.add(leave);
		LeaveDTO leaveDTO = new LeaveDTO();

		BeanUtils.copyProperties(leave, leaveDTO);
		List<LeaveDTO> leaveDTOs = new ArrayList<LeaveDTO>();
		leaveDTOs.add(leaveDTO);

		when(userService.getLeavsByUserId(TestData.userId)).thenReturn(leaveDTOs);

		List<LeaveDTO> LeaveDTOResponse = userService.getLeavsByUserId(TestData.userId);

		ResponseEntity<List<LeaveDTO>> responseEntity = userController.getLeavsByUserId(TestData.userId);

		assertThat(LeaveDTOResponse).isEqualTo(responseEntity.getBody());

	}
}
