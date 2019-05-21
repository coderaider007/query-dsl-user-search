package com.query.dsl.user.search.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;

import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.domain.Country;

public class CountryCommandToCountryTest {
	
	private CountryCommandToCountry countryCommandToCountry;

	@Before
	public void setUp() throws Exception {
		this.countryCommandToCountry = new CountryCommandToCountry();
	}

	@Test
	public void testConvert() {
		CountryCommand countryCommand = new CountryCommand();
		countryCommand.setId(Long.valueOf(1));
		countryCommand.setName("United States");
		
		Country country = this.countryCommandToCountry.convert(countryCommand);
		
		assertNotNull(country);
		assertEquals(Long.valueOf(1), country.getId());
	}

}
