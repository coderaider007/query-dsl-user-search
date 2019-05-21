package com.query.dsl.user.search.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.query.dsl.user.search.domain.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, QuerydslPredicateExecutor<Employee>{

}
