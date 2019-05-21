package com.query.dsl.user.search.command;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeSearchCommand {

	private Long id;
	private String firstName;
	private String lastName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDateLessThan;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDateGreaterThan;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDateLessThan;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDateGreaterThan;
	private String address;
	private Long positionId;
	private Long statusId;
	private Integer page;
	private Integer size;
	private String sort;
	private String error;
}
