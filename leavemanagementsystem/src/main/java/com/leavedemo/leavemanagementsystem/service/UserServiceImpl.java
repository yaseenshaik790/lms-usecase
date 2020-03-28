package com.leavedemo.leavemanagementsystem.service;

import java.time.DayOfWeek;
/**
 * UserServiceImpl is used to write business logic
 * @author YaseenShaik
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LeaveRepository leaveRepository;

	@Autowired
	private EmployeeLeaveRepository employeeLeaveRepository;

	/**
	 * Method is used to fetch leaves of particular user id
	 * 
	 * @throws UserNotFoundException        when user not exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws LeaveIdNotAvailableException when leave id doesn't exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws InvalidToDateException       when client enters invalid To date
	 * @throws InvalidFromDateException     when client enters invalid To date
	 */
	@Transactional
	public List<LeaveDTO> getLeavsByUserId(Long userId) {

		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new UserNotFoundException(userId);
		}

		List<Leave> leaves = leaveRepository.findByUserId(userId);

		if (CollectionUtils.isEmpty(leaves)) {
			throw new LeavesNotAvailableException(userId);
		}

		return leaves.stream().map(leave -> {
			LeaveDTO leaveDTO = new LeaveDTO();
			BeanUtils.copyProperties(leave, leaveDTO);
			return leaveDTO;
		}).collect(Collectors.toList());

	}

	/**
	 * Method is used to apply the leave
	 * 
	 * @throws UserNotFoundException        when user not exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws LeaveIdNotAvailableException when leave id doesn't exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws InvalidToDateException       when client enters invalid To date
	 * @throws InvalidFromDateException     when client enters invalid To date
	 */
	@Transactional
	public LeaveResponse applyLeave(Long userId, LeaveRequest leaveRequest) {

		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new UserNotFoundException(userId);
		}
		if (leaveRequest.getToDate() != null) {

			if (LocalDate.now().isAfter(leaveRequest.getFromDate())) {
				throw new InvalidFromDateException(leaveRequest.getFromDate());
			}

			if (leaveRequest.getFromDate().isAfter(leaveRequest.getToDate())) {
				throw new InvalidToDateException(leaveRequest.getToDate());
			}
			Long noOfDays = ChronoUnit.DAYS.between(leaveRequest.getFromDate(), leaveRequest.getToDate());

			Optional<Leave> leave = leaveRepository.findByLeaveIdAndUserId(leaveRequest.getLeaveId(), userId);
			if (!leave.isPresent()) {
				throw new LeavesNotAvailableException(leaveRequest.getLeaveId());

			}
			if (noOfDays > leave.get().getNoOfDays()) {
				throw new LeaveIdNotAvailableException(leaveRequest.getLeaveId());
			}
			leave.get().setNoOfDays((int) (leave.get().getNoOfDays() - noOfDays));
			leaveRepository.save(leave.get());
			EmployeeLeave employeeLeave = new EmployeeLeave(leaveRequest.getLeaveReason(), leaveRequest.getFromDate(),
					leaveRequest.getToDate(), noOfDays, userId);
			employeeLeaveRepository.save(employeeLeave);

			return new LeaveResponse("Leave approved successfully", 666);
		}
		return applyOneDayLeave(userId, leaveRequest);
	}

	/**
	 * Method is used to apply one day leave
	 * 
	 * @throws UserNotFoundException        when user not exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws LeaveIdNotAvailableException when leave id doesn't exist
	 * @throws LeavesNotAvailableException  when leaves not available
	 * @throws InvalidToDateException       when client enters invalid To date
	 * @throws InvalidFromDateException     when client enters invalid To date
	 */
	@Transactional
	public LeaveResponse applyOneDayLeave(Long userId, LeaveRequest leaveRequest) {

		DayOfWeek dayOfWeek = leaveRequest.getFromDate().getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY) {
			throw new WeekendDateException(leaveRequest.getFromDate());
		}
		if (LocalDate.now().isAfter(leaveRequest.getFromDate())) {
			throw new InvalidFromDateException(leaveRequest.getFromDate());
		}
		Optional<Leave> leave = leaveRepository.findByLeaveIdAndUserId(leaveRequest.getLeaveId(), userId);
		if (!leave.isPresent()) {
			throw new LeavesNotAvailableException(leaveRequest.getLeaveId());
		}
		if (1 >= leave.get().getNoOfDays()) {
			throw new LeaveIdNotAvailableException(1l);
		}
		leave.get().setNoOfDays((int) (leave.get().getNoOfDays() - 1));
		leaveRepository.save(leave.get());
		EmployeeLeave employeeLeave = new EmployeeLeave();
		employeeLeave.setLeaveFfromDate(leaveRequest.getFromDate());
		employeeLeave.setLeaveReason(leaveRequest.getLeaveReason());
		employeeLeave.setNoOfDays(1l);
		employeeLeaveRepository.save(employeeLeave);

		return new LeaveResponse("Leave approved successfully", 667);

	}

}
