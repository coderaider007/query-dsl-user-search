package com.query.dsl.user.search.command;


import java.util.Date;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeCommand {

	
	private Long id;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	@NotNull
	@Valid
	private AddressCommand addressCommand;
	
	@NotNull
	@Valid
	private PositionCommand positionCommand;
	
	@NotNull
	@Valid
	private StatusCommand statusCommand;
}
