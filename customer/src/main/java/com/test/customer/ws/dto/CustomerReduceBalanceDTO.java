package com.test.customer.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReduceBalanceDTO {

	private int customerId;

	private double amount;

}
