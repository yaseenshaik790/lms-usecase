package com.leavedemo.leavemanagementsystem.repository;
/**
 * EmployeeLeaveRepository interface is used to perform saving the user data and database all transactions
 * @author ShaikYaseen
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leavedemo.leavemanagementsystem.entity.EmployeeLeave;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {

}
