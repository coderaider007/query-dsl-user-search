package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.domain.State;

@Component
public class StateCommandToState implements Converter<StateCommand, State> {

	private final CountryCommandToCountry countryCommandToCountry;
	
	public StateCommandToState(CountryCommandToCountry countryCommandToCountry) {
		super();
		this.countryCommandToCountry = countryCommandToCountry;
	}

	@Override
	public State convert(StateCommand source) {
		if(source == null) {
			return null;
		}
		State state = new State();
		state.setId(source.getId());
		state.setCountry(this.countryCommandToCountry.convert(source.getCountryCommand()));
		state.setName(source.getName());
		return state;
	}

}
