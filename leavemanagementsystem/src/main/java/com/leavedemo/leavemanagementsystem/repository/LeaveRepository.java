package com.leavedemo.leavemanagementsystem.repository;

import java.util.List;
/**
 * LeaveRepository interface is used to perform saving the user data and all database  transactions
 * @author ShaikYaseen
 */
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leavedemo.leavemanagementsystem.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

	public List<Leave> findByUserId(Long userId);

	public Optional<Leave> findByLeaveIdAndUserId(Long leaveId, Long userId);

}
