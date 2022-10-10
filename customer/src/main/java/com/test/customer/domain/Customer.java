package com.test.customer.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Customer {

	private Integer id;

	private String name;

	private LocalDate birthday;

	private Gender gender;

	private String email;

	private BankAccount bankAccount;

	public void reduceCustomerAmount(double amount) {
		if (this.bankAccount.getRemainAmount() > amount) {
			this.bankAccount.setRemainAmount(this.bankAccount.getRemainAmount() - amount);
		}
	}

	public void addingBalance(double amount) {
		this.bankAccount.setRemainAmount(this.bankAccount.getRemainAmount() + amount);
	}
}
