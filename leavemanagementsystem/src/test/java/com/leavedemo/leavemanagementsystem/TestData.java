package com.leavedemo.leavemanagementsystem;

/**
 * creating static data for testing
 * @author ShaikYaseen
 */
import java.time.LocalDate;

public interface TestData {
	public static final Long userId = 10l;
	public static final String userName = "Yaseen Shaik";
	public static final String password = "123";
	public static final String email = "yaseen@gmail.com";
	public static final Long userId1 = 20l;
	public static final String userName1 = "Naveen Kumar";
	public static final String password1 = "222";
	public static final String email1 = "naveen@gmail.com";
	public static final Long leaveId = 10l;
	public static final String leaveCategery = "Restricted Holidays";
	public static final Integer noOfDays = 22;
	public static final Long leaveId1 = 20l;
	public static final String leaveCategery1 = "Annual Holidays";
	public static final Integer noOfDays1 = 4;
	public static final Long leaveId2 = 30l;
	public static final String leaveCategery2 = "My Leave";
	public static final Integer noOfDays2 = 6;
	public static final Long employeeLeaveId = 10l;
	public static final String leaveReason = "My Leave";
	public static final LocalDate leavefromDate = LocalDate.of(2020, 03, 30);
	public static final LocalDate leaveToDate = LocalDate.of(2020, 04, 04);
	public static final Long noOfDay = 3l;

}
