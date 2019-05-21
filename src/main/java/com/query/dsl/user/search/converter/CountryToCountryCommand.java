package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.domain.Country;

@Component
public class CountryToCountryCommand implements Converter<Country, CountryCommand> {

	@Override
	public CountryCommand convert(Country source) {
		if(source == null) {
			return null;
		}
		CountryCommand countryCommand = new CountryCommand();
		countryCommand.setId(source.getId());
		countryCommand.setName(source.getName());
		return countryCommand;
	}

}
