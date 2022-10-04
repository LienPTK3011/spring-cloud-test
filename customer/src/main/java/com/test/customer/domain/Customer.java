package com.test.customer.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private int id;

	private String name;

	private LocalDate birthday;

	private Gender gender;

	private String email;
	
	private BankAccount bankAccount;
	
	public void reduceCustomerAmount(double amount) {
		if (this.bankAccount.getRemainAmount()  > amount) {
			this.bankAccount.setRemainAmount(this.bankAccount.getRemainAmount() - amount);
		}
	}
}
