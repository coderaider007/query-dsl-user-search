package com.query.dsl.user.search.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.domain.Country;
import com.query.dsl.user.search.domain.State;

public class StateToStateCommandTest {
	
	private StateToStateCommand stateToStateCommand;
	
	@Before
	public void setUp() throws Exception {
		this.stateToStateCommand = new StateToStateCommand(new CountryToCountryCommand());
	}

	@Test
	public void testConvert() {
		State state = new State();
		state.setId(Long.valueOf(1));
		state.setName("Wisconsin");
		
		Country country = new Country();
		country.setId(Long.valueOf(1));
		country.setName("United States");
		state.setCountry(country);
		
		StateCommand stateCommand = this.stateToStateCommand.convert(state);
		
		assertNotNull(stateCommand);
		assertEquals(Long.valueOf(1), stateCommand.getId());
	}

}
