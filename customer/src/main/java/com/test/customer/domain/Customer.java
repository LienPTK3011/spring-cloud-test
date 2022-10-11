package com.test.customer.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
	
	@Override
	public boolean equals(Object obj) {
		Customer customer = (Customer) obj;
		return this.getId() == customer.getId();
	}

	public void reduceCustomerAmount(double amount) {
		if (this.bankAccount.getRemainAmount() > amount) {
			this.bankAccount.setRemainAmount(this.bankAccount.getRemainAmount() - amount);
		}
	}

	public void addingBalance(double amount) {
		this.bankAccount.setRemainAmount(this.bankAccount.getRemainAmount() + amount);
	}
}
