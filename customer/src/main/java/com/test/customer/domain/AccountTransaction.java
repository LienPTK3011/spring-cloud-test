package com.test.customer.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AccountTransaction {

	private double amount;

	private LocalDateTime transactionDate;

	private BankAccount bankAccount;

}
