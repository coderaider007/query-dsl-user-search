package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.domain.Address;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand, Address> {
	
	private final CountryCommandToCountry countryCommandToCountry;
	private final StateCommandToState stateCommandToState;
	
	public AddressCommandToAddress(CountryCommandToCountry countryCommandToCountry,
			StateCommandToState stateCommandToState) {
		super();
		this.countryCommandToCountry = countryCommandToCountry;
		this.stateCommandToState = stateCommandToState;
	}

	@Override
	public Address convert(AddressCommand source) {
		if(source == null) {
			return null;
		}
		Address address = new Address();
		address.setAddress1(source.getAddress1());
		address.setAddress2(source.getAddress2());
		address.setCity(source.getCity());
		address.setCountry(this.countryCommandToCountry.convert(source.getCountryCommand()));
		address.setId(source.getId());
		address.setIsMainAddress(source.getIsMainAddress());
		address.setPostalCode(source.getPostalCode());
		address.setVersion(source.getVersion());
		address.setState(this.stateCommandToState.convert(source.getStateCommand()));
		return address;
	}

}
