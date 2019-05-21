package com.query.dsl.user.search.repository;

import org.springframework.data.repository.CrudRepository;

import com.query.dsl.user.search.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

}
