package com.team6.repository;

import org.springframework.data.repository.CrudRepository;

import com.team6.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

}
