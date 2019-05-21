package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.domain.Country;

@Component
public class CountryCommandToCountry implements Converter<CountryCommand, Country> {

	@Override
	public Country convert(CountryCommand source) {
		if(source == null) {
			return null;
		}
		Country country = new Country();
		country.setId(source.getId());
		country.setName(source.getName());
		return country;
	}

}
