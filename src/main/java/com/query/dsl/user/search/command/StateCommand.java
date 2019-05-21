package com.query.dsl.user.search.command;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class StateCommand {

	@NotNull
	private Long id;
	private String name;
	private CountryCommand countryCommand;
}
