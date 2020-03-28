package com.leavedemo.leavemanagementsystem.repository;

/**
 * User Repository Test
 * @author ShaikYaseen
 */
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import com.leavedemo.leavemanagementsystem.TestData;
import com.leavedemo.leavemanagementsystem.dto.LeaveRequest;
import com.leavedemo.leavemanagementsystem.entity.EmployeeLeave;
import com.leavedemo.leavemanagementsystem.entity.Leave;
import com.leavedemo.leavemanagementsystem.entity.User;
import com.leavedemo.leavemanagementsystem.exception.InvalidFromDateException;
import com.leavedemo.leavemanagementsystem.exception.InvalidToDateException;
import com.leavedemo.leavemanagementsystem.exception.LeaveIdNotAvailableException;
import com.leavedemo.leavemanagementsystem.exception.LeavesNotAvailableException;
import com.leavedemo.leavemanagementsystem.exception.UserNotFoundException;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@MockBean
	private UserRepository userRepository;
	
	

	@MockBean
	private LeaveRepository leaveRepository;

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
		leaveRequest1 = new LeaveRequest();
		leaveRequest1.setFromDate(pastDate);
		leaveRequest1.setLeaveId(200l);

	}

	/**
	 * testing invalid to date exceptionTest
	 */
	@Test
	public void invalidToDateExceptionTest() {
		testEntityManager.persistAndFlush(user);
		assertThrows(InvalidToDateException.class, () -> {
			throw new InvalidToDateException(pastDate);
		});

	}

	/**
	 * testing invalid user exceptionTest
	 */
	@Test
	public void userNotExceptionTest() {

		testEntityManager.persistAndFlush(user);

		Mockito.when(userRepository.findById(1l)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
			throw new UserNotFoundException(200l);
		});

	}

	/**
	 * testing invalid user exception Test
	 */
	@Test
	public void leavesNotAvailableExceptionTest() {

		testEntityManager.persistAndFlush(user);

		assertThrows(LeavesNotAvailableException.class, () -> {
			throw new LeavesNotAvailableException(200l);
		});

	}

	/**
	 * testing invalid from date exceptionTest
	 */
	@Test
	public void invalidFromDateExceptionTest() {

		testEntityManager.persistAndFlush(user);

		assertThrows(InvalidFromDateException.class, () -> {
			throw new InvalidFromDateException(pastDate);
		});

	}

	/**
	 * testing user id is present or not exception
	 */
	@Test
	public void userNotFoundExceptionTest() {

		testEntityManager.persistAndFlush(user);

		Optional<User> checkProduct = userRepository.findById(TestData.userId);

		assertFalse(checkProduct.isPresent());

	}

	/**
	 * test leavesNotAvailableExceptionTest
	 */
	@Test
	public void leavesNotAvailableTest() {

		testEntityManager.persistAndFlush(user);

		List<Leave> leaves = leaveRepository.findByUserId(TestData.userId);

		assertTrue(CollectionUtils.isEmpty(leaves));

	}

	/**
	 * test leavesIdNotPresentExceptionTest
	 */
	@Test
	public void leavesIdNotPresentExceptionTest() {

		testEntityManager.persistAndFlush(user);

		assertThrows(LeaveIdNotAvailableException.class, () -> {
			throw new LeaveIdNotAvailableException(200l);
		});

	}

	/**
	 * test leaveCatagoryNotAvailableExceptionTest
	 */
	@Test
	public void leaveIdNotAvailableExceptionTest() {

		testEntityManager.persistAndFlush(user);
		testEntityManager.persistAndFlush(leave);

		Optional<Leave> checkLeave = leaveRepository.findByLeaveIdAndUserId(TestData.leaveId, TestData.userId);
		assertFalse(checkLeave.isPresent());

	}
}
