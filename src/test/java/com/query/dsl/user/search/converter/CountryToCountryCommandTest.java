package com.query.dsl.user.search.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.domain.Country;

public class CountryToCountryCommandTest {

	private CountryToCountryCommand countryToCountryCommand;
	
	@Before
	public void setUp() throws Exception {
		this.countryToCountryCommand = new CountryToCountryCommand();
	}

	@Test
	public void testConvert() {
		Country country = new Country();
		country.setId(Long.valueOf(1));
		country.setName("China");
		
		CountryCommand countryCommand = this.countryToCountryCommand.convert(country);
		
		assertNotNull(countryCommand);
		assertEquals("China", country.getName());
	}

}
