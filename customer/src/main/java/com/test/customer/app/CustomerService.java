package com.test.customer.app;

import com.test.customer.domain.Customer;
import com.test.customer.ws.dto.CustomerDTO;

public interface CustomerService {

	/**
	 * reduce customer amount when purchase product
	 * 
	 * @param customerId: customer id
	 * @param amount: purchase amount
	 */
	public Customer reduceCustomerDepositAmount(int customerId, double amount);

	public Customer addingBalance(int customerId, double amount);

	public CustomerDTO saveCustomer(CustomerDTO customerDTO);
}
