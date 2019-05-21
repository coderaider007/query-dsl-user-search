package com.query.dsl.user.search.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.domain.Address;

import net.bytebuddy.asm.Advice.This;

public class AddressCommandToAddressTest {

	private AddressCommandToAddress addressCommandToAddress;
	
	@Before
	public void setUp() throws Exception {
		this.addressCommandToAddress = new AddressCommandToAddress(new CountryCommandToCountry(), new StateCommandToState(new CountryCommandToCountry()));
	}

	@Test
	public void testConvert() {
		CountryCommand countryCommand = new CountryCommand();
		countryCommand.setId(Long.valueOf(1));
		countryCommand.setName("United States");
		
		StateCommand stateCommand = new StateCommand();
		stateCommand.setCountryCommand(countryCommand);
		stateCommand.setId(Long.valueOf(5));
		stateCommand.setName("California");
		
		
		AddressCommand addressCommand = new AddressCommand();
		addressCommand.setAddress1("123 Avenue Foch");
		addressCommand.setAddress2(null);
		addressCommand.setCity("Happy Town");
		addressCommand.setId(Long.valueOf(2));
		addressCommand.setIsMainAddress(true);
		addressCommand.setPostalCode("90112");
		addressCommand.setVersion(Long.valueOf(1));
		addressCommand.setCountryCommand(countryCommand);
		addressCommand.setStateCommand(stateCommand);
		
		Address address = this.addressCommandToAddress.convert(addressCommand);
		
		assertNotNull(address);
		assertEquals(Long.valueOf(2), address.getId());
		assertNotNull(address.getCountry());
		assertEquals(Long.valueOf(1), address.getCountry().getId());
	}

}
