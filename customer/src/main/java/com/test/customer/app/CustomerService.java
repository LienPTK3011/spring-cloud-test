package com.test.customer.app;

import com.test.customer.ws.dto.CustomerDTO;

public interface CustomerService {

	/**
	 * reduce customer amount when purchase product
	 * 
	 * @param customerId: customer id
	 * @param amount: purchase amount
	 */
	public void reduceCustomerDepositAmount(int customerId, double amount);

	public void addingBalance(int customerId, double amount);

	public void saveCustomer(CustomerDTO customerDTO);
}
