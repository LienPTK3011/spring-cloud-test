package com.test.sale.app.external;

import com.test.sale.app.external.queryparams.CustomerReduceBalanceDTO;

import feign.Headers;
import feign.RequestLine;

public interface CustomerClientService {

	/**
	 * reduce customer amount when purchase product
	 * 
	 * @param customerId: customer id
	 * @param amount: purchase amount
	 */
	@RequestLine("POST /reduce-customer-amount")
    @Headers("Content-Type: application/json")
	public void reduceCustomerDepositAmount(CustomerReduceBalanceDTO balanceDTO);

}
