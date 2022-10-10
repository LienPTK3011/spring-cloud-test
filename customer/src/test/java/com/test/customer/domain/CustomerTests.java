package com.test.customer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTests {

	private Customer customer;
	
	@BeforeEach
	void setupData() {
		this.customer = Customer.builder()
				.id(1)
				.name("Trương Quang Hà")
				.email("hatq@gmail.com")
				.birthday(LocalDate.of(1995, 11, 30))
				.gender(Gender.MALE)
				.bankAccount(BankAccount.builder()
						.id(2)
						.bankAccountNumber("1234567890")
						.cardLimit(90000000d)
						.expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(90000000d)
						.build()
				).build();
	}

	@Test
    void testReduceCustomerAmount() {

		// remain amount
		double amountRemain = this.customer.getBankAccount().getRemainAmount() - 10000;
		
		customer.reduceCustomerAmount(10000);

        assertEquals(amountRemain, customer.getBankAccount().getRemainAmount(), "Customer reduce amounnt");
    }
	
	@Test
    void testAddBalanceAccount() {
		// remain amount
		double amountRemain = this.customer.getBankAccount().getRemainAmount() + 10000;
		
		customer.addingBalance(10000);

        assertEquals(amountRemain, customer.getBankAccount().getRemainAmount(), "Customer add balance amounnt");
    }
}
