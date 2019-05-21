package com.query.dsl.user.search.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.domain.Address;
import com.query.dsl.user.search.domain.Country;
import com.query.dsl.user.search.domain.State;

public class AddressToAddressCommandTest {
	
	
	private AddressToAddressCommand addressToAddressCommand;
	
	@Before
	public void setUp() throws Exception {
		
		this.addressToAddressCommand = new AddressToAddressCommand(new CountryToCountryCommand(), new StateToStateCommand(new CountryToCountryCommand()));
	}

	@Test
	public void testConvert() {
		Country country = new Country();
		country.setId(Long.valueOf(1));
		country.setName("United States");
		
		State state = new State();
		state.setCountry(country);
		state.setId(Long.valueOf(5));
		state.setName("California");
		
		Address address = new Address();
		address.setAddress1("123 Avenue Foch");
		address.setAddress2(null);
		address.setCity("Happy Town");
		address.setId(Long.valueOf(2));
		address.setIsMainAddress(true);
		address.setPostalCode("90112");
		address.setVersion(Long.valueOf(1));
		address.setCountry(country);
		address.setState(state);
		
		AddressCommand addressCommand = this.addressToAddressCommand.convert(address);
		
		assertNotNull(addressCommand);
		assertEquals(Long.valueOf(2), addressCommand.getId());
		assertNotNull(addressCommand.getCountryCommand());
		assertEquals(Long.valueOf(1), addressCommand.getCountryCommand().getId());
	}

}
