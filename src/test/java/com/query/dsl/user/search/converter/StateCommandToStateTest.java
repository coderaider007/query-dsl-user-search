package com.query.dsl.user.search.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jayway.jsonpath.internal.function.text.Concatenate;
import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.domain.State;

public class StateCommandToStateTest {

	private StateCommandToState stateCommandToState;
	
	@Before
	public void setUp() throws Exception {
		this.stateCommandToState = new StateCommandToState(new CountryCommandToCountry());
	}

	@Test
	public void testConvert() {
		StateCommand stateCommand = new StateCommand();
		stateCommand.setId(Long.valueOf(1));
		stateCommand.setName("Florida");
		
		CountryCommand countryCommand = new CountryCommand();
		countryCommand.setId(Long.valueOf(2));
		countryCommand.setName("United States");
		stateCommand.setCountryCommand(countryCommand);
		
		State state = this.stateCommandToState.convert(stateCommand);
		
		assertNotNull(state);
		assertEquals(Long.valueOf(1), state.getId());
	}
	
	@Test
	public void testConvertNull() {
		State state = this.stateCommandToState.convert(null);
		assertNull(state);
	}

}
