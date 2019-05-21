package com.query.dsl.user.search.command;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PositionCommand {

	@NotNull
	private Long id;
	private String position;
}
