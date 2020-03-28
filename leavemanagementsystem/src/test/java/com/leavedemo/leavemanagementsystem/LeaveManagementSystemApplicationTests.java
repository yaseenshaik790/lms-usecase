package com.leavedemo.leavemanagementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * To run all test cases 
 * @author ShaikYaseen
 */
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LeaveManagementSystemApplicationTests {
	/**
	 * Simple test
	 */
	@Test
	void contextLoads() {
		assertEquals(5, Math.subtractExact(10, 5));

	}

}
