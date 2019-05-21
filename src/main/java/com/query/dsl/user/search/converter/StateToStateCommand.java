package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.domain.State;

@Component
public class StateToStateCommand implements Converter<State, StateCommand> {

	private final CountryToCountryCommand countryToCountryCommand;
	
	public StateToStateCommand(CountryToCountryCommand countryToCountryCommand) {
		super();
		this.countryToCountryCommand = countryToCountryCommand;
	}

	@Override
	public StateCommand convert(State source) {
		if(source == null) {
			return null;
		}
		StateCommand stateCommand = new StateCommand();
		stateCommand.setId(source.getId());
		stateCommand.setCountryCommand(this.countryToCountryCommand.convert(source.getCountry()));
		stateCommand.setName(source.getName());
		return stateCommand;
	}

}
