package com.query.dsl.user.search.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.converter.CountryToCountryCommand;
import com.query.dsl.user.search.domain.Country;
import com.query.dsl.user.search.repository.CountryRepository;
import com.query.dsl.user.search.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	private final CountryRepository countryRepository;
	private final CountryToCountryCommand countryToCountryCommand;
	
	public CountryServiceImpl(CountryRepository countryRepository, CountryToCountryCommand countryToCountryCommand) {
		super();
		this.countryRepository = countryRepository;
		this.countryToCountryCommand = countryToCountryCommand;
	}

	@Override
	public List<CountryCommand> loadAllCountries() {
		List<CountryCommand> countryCommands = new ArrayList<>();
		Iterable<Country> countries = this.countryRepository.findAll(); 
		countries.forEach(country -> countryCommands.add(this.countryToCountryCommand.convert(country)));
		return countryCommands;
	}

}
