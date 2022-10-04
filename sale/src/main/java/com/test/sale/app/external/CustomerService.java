package com.test.sale.app.external;

public interface CustomerService {
	/**
	 * reduce customer amount when purchase product
	 * 
	 * @param customerId: customer id
	 * @param amount: purchase amount
	 */
	public void reduceCustomerDepositAmount(int customerId, double amount);
}
