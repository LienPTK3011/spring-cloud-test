package com.test.customer.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerReduceBalanceDTO {

	private int customerId;

	private double amount;

}
