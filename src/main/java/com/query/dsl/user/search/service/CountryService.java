package com.query.dsl.user.search.service;

import java.util.List;

import com.query.dsl.user.search.command.CountryCommand;

public interface CountryService{

	public List<CountryCommand> loadAllCountries();
}
