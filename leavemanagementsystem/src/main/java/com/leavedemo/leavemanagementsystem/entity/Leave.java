package com.leavedemo.leavemanagementsystem.entity;

/**
 * Leave class is used to store leave details
 * @author ShaikYaseen
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leave")
@Getter
@Setter
public class Leave implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leave_id")
	private Long leaveId;

	@Column(name = "leave_categery")
	private String leaveCategery;

	@Column(name = "no_of_days")
	private Integer noOfDays;

	@JoinColumn(name = "user_id")
	private Long userId;

}
