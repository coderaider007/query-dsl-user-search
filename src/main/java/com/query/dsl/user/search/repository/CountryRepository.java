package com.query.dsl.user.search.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.query.dsl.user.search.domain.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {

	List<Country> findByName(String name);
}
