package com.leavedemo.leavemanagementsystem.repository;

/**
 * UserRepository interface is used to perform saving the user data and all database  transactions
 * @author ShaikYaseen
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leavedemo.leavemanagementsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
