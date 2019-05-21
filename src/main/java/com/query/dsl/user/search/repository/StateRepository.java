package com.query.dsl.user.search.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.query.dsl.user.search.domain.State;

public interface StateRepository extends PagingAndSortingRepository<State, Long> {

	List<State> findByName(String name);
}
