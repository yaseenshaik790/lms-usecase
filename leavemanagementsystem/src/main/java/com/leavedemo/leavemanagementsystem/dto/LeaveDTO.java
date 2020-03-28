package com.leavedemo.leavemanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LeaveDTO {

	private Long leaveId;

	private String leaveCategery;

	private Integer noOfDays;

	private Long userId;

}
