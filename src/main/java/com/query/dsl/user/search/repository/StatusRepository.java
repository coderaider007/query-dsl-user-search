package com.query.dsl.user.search.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.query.dsl.user.search.domain.Status;

public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {

}
