package com.test.customer.infra.repository;

import java.util.Optional;

import com.test.customer.domain.Customer;

public interface CustomerRepository {
	
	/**
	 * Update customer info
	 * 
	 * @param domain customer
	 */
	public void update(Customer customer);
	
	/**
	 * Find customer by id
	 * 
	 * @param customerId
	 * @return domain customer
	 */
	public Optional<Customer> findById(int customerId);
}
