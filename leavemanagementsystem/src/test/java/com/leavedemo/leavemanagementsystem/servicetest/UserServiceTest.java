package com.leavedemo.leavemanagementsystem.servicetest;

/**
 * User Service test cases
 * 
 * @author ShaikYaseen
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import com.leavedemo.leavemanagementsystem.TestData;
import com.leavedemo.leavemanagementsystem.dto.LeaveDTO;
import com.leavedemo.leavemanagementsystem.dto.LeaveRequest;
import com.leavedemo.leavemanagementsystem.entity.EmployeeLeave;
import com.leavedemo.leavemanagementsystem.entity.Leave;
import com.leavedemo.leavemanagementsystem.entity.User;
import com.leavedemo.leavemanagementsystem.exception.InvalidFromDateException;
import com.leavedemo.leavemanagementsystem.exception.InvalidToDateException;
import com.leavedemo.leavemanagementsystem.exception.LeaveIdNotAvailableException;
import com.leavedemo.leavemanagementsystem.exception.LeavesNotAvailableException;
import com.leavedemo.leavemanagementsystem.exception.UserNotFoundException;
import com.leavedemo.leavemanagementsystem.exception.WeekendDateException;
import com.leavedemo.leavemanagementsystem.repository.EmployeeLeaveRepository;
import com.leavedemo.leavemanagementsystem.repository.LeaveRepository;
import com.leavedemo.leavemanagementsystem.repository.UserRepository;
import com.leavedemo.leavemanagementsystem.response.LeaveResponse;
import com.leavedemo.leavemanagementsystem.service.UserService;

@SpringBootTest
public class UserServiceTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@MockBean
	private LeaveRepository leaveRepository;

	@MockBean
	private EmployeeLeaveRepository employeeLeaveRepository;

	User user, user1;
	Leave leave, leave1;
	EmployeeLeave employeeLeave, employeeLeave1;
	LeaveRequest leaveRequest, leaveRequest1;
	private static LocalDate pastDate = LocalDate.of(1998, 01, 07);

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
		leaveRequest.setToDate(TestData.leaveToDate);
		leaveRequest1 = new LeaveRequest();
		leaveRequest1.setFromDate(pastDate);
		leaveRequest1.setLeaveId(200l);

	}

	/**
	 * testing apply for an leave
	 */
	@Test
	public void testApplyLeavePositive() {
		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		when(leaveRepository.save(leave)).thenReturn(leave);
		when(employeeLeaveRepository.save(employeeLeave)).thenReturn(employeeLeave);
		when(leaveRepository.findByLeaveIdAndUserId(TestData.leaveId, TestData.userId)).thenReturn(Optional.of(leave));
		LeaveResponse leaveResponse = new LeaveResponse("Leave approved successfully", 666);
		LeaveResponse leaveServiceResponse = userService.applyLeave(TestData.userId, leaveRequest);
		assertThat(leaveServiceResponse.getMessage()).isEqualTo(leaveResponse.getMessage());
		assertThat(leaveServiceResponse.getStatusCode()).isEqualTo(leaveResponse.getStatusCode());

	}

	/**
	 * testing apply for an leave in negative scenarios
	 */
	@Test
	public void testApplyLeaveNegative() {

		when(userRepository.findById(200l)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			throw new UserNotFoundException(200l);
		});

		assertThrows(InvalidFromDateException.class, () -> {
			throw new InvalidFromDateException(pastDate);
		});
		assertThrows(InvalidToDateException.class, () -> {
			throw new InvalidToDateException(pastDate);
		});

		Optional<Leave> leave = leaveRepository.findByLeaveIdAndUserId(300l, 200l);
		assertFalse(leave.isPresent());
		assertThrows(LeavesNotAvailableException.class, () -> {
			throw new LeavesNotAvailableException(200l);
		});
		assertFalse(leave.isPresent());
		assertThrows(LeaveIdNotAvailableException.class, () -> {
			throw new LeaveIdNotAvailableException(200l);
		});

	}

	/**
	 * testing to apply leave for more than one day
	 */
	@Test
	public void testApplyOneDayLeavePositive() {
		leaveRequest1 = new LeaveRequest();
		leaveRequest1.setFromDate(TestData.leavefromDate);
		leaveRequest1.setLeaveId(TestData.leaveId);

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));

		when(leaveRepository.save(leave)).thenReturn(leave);

		when(employeeLeaveRepository.save(employeeLeave)).thenReturn(employeeLeave);

		when(leaveRepository.findByLeaveIdAndUserId(TestData.leaveId, TestData.userId)).thenReturn(Optional.of(leave));

		LeaveResponse leaveResponse = new LeaveResponse("Leave approved successfully", 667);

		LeaveResponse leaveServiceResponse = userService.applyLeave(TestData.userId, leaveRequest1);

		assertThat(leaveServiceResponse.getMessage()).isEqualTo(leaveResponse.getMessage());
		assertThat(leaveServiceResponse.getStatusCode()).isEqualTo(leaveResponse.getStatusCode());
	}

	/**
	 * test getLeaves ByUserId when user doesn't exist
	 */
	@Test
	public void testgetLeavesByUserIdThrowsUserNotFoundException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
			userService.getLeavsByUserId(TestData.userId);
		});

	}

	/**
	 * test leaves are not available exception
	 */
	@Test
	public void testgetLeavesByUserIdThrowsLeavesNotAvailableException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		List<Leave> leaves = new ArrayList<>();
		LeaveDTO leaveDTO = new LeaveDTO();
		BeanUtils.copyProperties(leave, leaveDTO);
		when(leaveRepository.findByUserId(TestData.userId)).thenReturn(leaves);

		assertThrows(LeavesNotAvailableException.class, () -> {
			userService.getLeavsByUserId(TestData.userId);
		});

	}

	/**
	 * test when user is doesn't exist to apply leave
	 */
	@Test
	public void testapplyLeaveThrowsUserNotFoundException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
			userService.applyLeave(TestData.userId, leaveRequest);
		});
	}

	/**
	 * test from date is valid or not
	 */
	@Test
	public void testapplyLeaveThrowsInvalidFromDateException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		LeaveRequest leavetest = new LeaveRequest();
		leavetest.setFromDate(pastDate);
		leavetest.setToDate(pastDate);

		assertThrows(InvalidFromDateException.class, () -> {
			userService.applyLeave(TestData.userId, leavetest);
		});

	}

	/**
	 * test from date is valid or not to apply leave
	 */
	@Test
	public void testapplyOneDayLeavethrowsInvalidFromDateException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		LeaveRequest leavetest = new LeaveRequest();
		leavetest.setFromDate(pastDate);

		assertThrows(InvalidFromDateException.class, () -> {
			userService.applyLeave(TestData.userId, leavetest);
		});

	}
	
	/**
	 * test from date is valid or not to apply leave
	 */
	@Test
	public void testapplyOneDayLeavethrowsWeekendDateException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		LeaveRequest leavetest = new LeaveRequest();
		leavetest.setFromDate(LocalDate.of(2020, 03, 29));

		assertThrows(WeekendDateException.class, () -> {
			userService.applyLeave(TestData.userId, leavetest);
		});

	}


	/**
	 * test user entered data is valid or not
	 */
	@Test
	public void testapplyLeaveThrowsInvalidToDateException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		LeaveRequest leavetest = new LeaveRequest();
		leavetest.setFromDate(TestData.leavefromDate);
		leavetest.setToDate(pastDate);

		assertThrows(InvalidToDateException.class, () -> {
			userService.applyLeave(TestData.userId, leavetest);
		});

	}

	/**
	 * test leaves are available or not to the user
	 */
	@Test
	public void testapplyLeavethrowsLeavesNotAvailableException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		LeaveRequest leavetest = new LeaveRequest();
		leavetest.setFromDate(TestData.leavefromDate);
		leavetest.setToDate(TestData.leaveToDate);
		when(leaveRepository.findByLeaveIdAndUserId(TestData.leaveId, TestData.userId)).thenReturn(Optional.empty());

		assertThrows(LeavesNotAvailableException.class, () -> {
			userService.applyLeave(TestData.userId, leavetest);
		});

	}

	/**
	 * test leaves are not available when client gives request
	 */
	@Test
	public void testapplyOneDayLeaveThrowsLeavesNotAvailableException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		LeaveRequest leavetest = new LeaveRequest();
		leavetest.setFromDate(LocalDate.of(2020, 03, 30));
		when(leaveRepository.findByLeaveIdAndUserId(1l, TestData.userId)).thenReturn(Optional.of(leave));

		assertThrows(LeavesNotAvailableException.class, () -> {
			userService.applyLeave(TestData.userId, leavetest);
		});

	}

	/**
	 * test leaves available or not of perticular user
	 */
	@Test
	public void testapplyLeaveThrowsLeaveIdNotAvailableException() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		LeaveRequest leavetest = new LeaveRequest();
		leavetest.setFromDate(LocalDate.of(2020, 03, 29));
		leavetest.setToDate(LocalDate.of(2020, 05, 03));
		leavetest.setLeaveId(1l);
		when(leaveRepository.findByLeaveIdAndUserId(1l, TestData.userId)).thenReturn(Optional.of(leave));

		assertThrows(LeaveIdNotAvailableException.class, () -> {
			userService.applyLeave(TestData.userId, leavetest);
		});

	}

	/**
	 * these method is used for testing user not found exception and leaves not
	 * available exception in apply leave
	 */
	@Test
	public void getLeavsByUserIdThrowsExceptionNegative() {

		when(userRepository.findById(200l)).thenReturn(Optional.of(user));
		when(userRepository.findById(200l)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			throw new UserNotFoundException(200l);
		});
		List<LeaveDTO> leaveDTOsRespose = new ArrayList<>();
		assertTrue(CollectionUtils.isEmpty(leaveDTOsRespose));

		assertThrows(LeavesNotAvailableException.class, () -> {
			throw new LeavesNotAvailableException(200l);
		});

	}

	/**
	 * testing to apply leave for one day
	 */
	@Test
	public void testApplyOneDayLeaveNegative() {

		when(userRepository.findById(200l)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			throw new UserNotFoundException(200l);
		});

		assertThrows(InvalidFromDateException.class, () -> {
			throw new InvalidFromDateException(pastDate);
		});

		Optional<Leave> leave = leaveRepository.findByLeaveIdAndUserId(300l, 200l);
		assertFalse(leave.isPresent());
		assertThrows(LeavesNotAvailableException.class, () -> {
			throw new LeavesNotAvailableException(200l);
		});

		assertFalse(leave.isPresent());
		assertThrows(LeaveIdNotAvailableException.class, () -> {
			throw new LeaveIdNotAvailableException(200l);
		});

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

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));

		when(leaveRepository.findByUserId(TestData.userId)).thenReturn(leaves);

		List<LeaveDTO> LeaveDTOsRespose = userService.getLeavsByUserId(TestData.userId);

		assertFalse(CollectionUtils.isEmpty(LeaveDTOsRespose));

		verify(leaveRepository, times(1)).findByUserId(TestData.userId);

	}

}
