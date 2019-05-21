package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.domain.Address;

@Component
public class AddressToAddressCommand implements Converter<Address, AddressCommand> {

	private final CountryToCountryCommand countryToCountryCommand;
	private final StateToStateCommand stateToStateCommand;
	
	public AddressToAddressCommand(CountryToCountryCommand countryToCountryCommand,
			StateToStateCommand stateToStateCommand) {
		super();
		this.countryToCountryCommand = countryToCountryCommand;
		this.stateToStateCommand = stateToStateCommand;
	}

	@Override
	public AddressCommand convert(Address source) {
		if(source == null) {
			return null;
		}
		AddressCommand addressCommand = new AddressCommand();
		addressCommand.setAddress1(source.getAddress1());
		addressCommand.setAddress2(source.getAddress2());
		addressCommand.setCity(source.getCity());
		addressCommand.setCountryCommand(this.countryToCountryCommand.convert(source.getCountry()));
		addressCommand.setId(source.getId());
		addressCommand.setIsMainAddress(source.getIsMainAddress());
		addressCommand.setPostalCode(source.getPostalCode());
		addressCommand.setVersion(source.getVersion());
		addressCommand.setStateCommand(this.stateToStateCommand.convert(source.getState()));
		return addressCommand;
	}

}
