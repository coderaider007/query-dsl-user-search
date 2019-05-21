package com.query.dsl.user.search.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.query.dsl.user.search.domain.Position;

public interface PositionRepository extends PagingAndSortingRepository<Position, Long> {

}
