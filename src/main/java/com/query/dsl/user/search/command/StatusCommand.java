package com.query.dsl.user.search.command;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StatusCommand {

	@NotNull
	private Long id;
	private String status;
}
