package com.test.customer.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.customer.domain.BankAccount;
import com.test.customer.domain.Customer;
import com.test.customer.domain.Gender;
import com.test.customer.helper.DateHelper;
import com.test.customer.infra.repository.CustomerRepository;
import com.test.customer.infra.repository.account.AccountTransactionRepository;
import com.test.customer.ws.dto.BankAccountDTO;
import com.test.customer.ws.dto.CustomerDTO;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();

	@Mock
	CustomerRepository customerRepository;

	@Mock
	AccountTransactionRepository accountTransactionRepository;

	private CustomerDTO customer;

	@BeforeEach
	void setupData() {
		this.customer = CustomerDTO.builder()
				.id(1)
				.name("Trương Quang Hà")
				.email("hatq@gmail.com")
				.birthday("1995-11-30")
				.gender(Gender.MALE.value)
				.bankAccount(
					BankAccountDTO.builder()
						.id(1)
						.bankAccountNumber("1234567890")
						.cardLimit(90000000d)
						.build()
				).build();
	}
	
	@Test
	void saveCustomer() {
		Customer domain = Customer.builder()
 				.id(customer.getId())
 				.name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(Gender.valueOf(customer.getGender()))
				.bankAccount(BankAccount.builder().id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit()).expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit()).build())
				.build();
		Customer mockResult = domain;
		Mockito.lenient().when(customerRepository.save(domain)).thenReturn(mockResult);
		customerService.saveCustomer(customer);
	}

	@Test
	void test_reduce_customer_amount() {
 		Double reductionNumber = 10d;

 		Customer domain = Customer.builder()
 				.id(customer.getId())
 				.name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(Gender.valueOf(customer.getGender()))
				.bankAccount(BankAccount.builder().id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit()).expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit()).build())
				.build();

		Mockito.when(customerRepository.findById(this.customer.getId())).thenReturn(Optional.of(domain));

		Customer ex = customerService.reduceCustomerDepositAmount(customer.getId(), reductionNumber);

		domain.reduceCustomerAmount(reductionNumber);
		assertEquals(domain.getBankAccount().getRemainAmount(), ex.getBankAccount().getRemainAmount());
	}

	
	@Test
	void test_reduce_customer_amount_not_exist() {
 		Double reductionNumber = 10d;
		Customer ex = customerService.reduceCustomerDepositAmount(customer.getId(), reductionNumber);
		assertEquals(null, ex);
	}

	@Test
	void test_add_customer_amount() {
		Double additionNumber = 10d;

 		Customer domain = Customer.builder()
 				.id(customer.getId())
 				.name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(Gender.valueOf(customer.getGender()))
				.bankAccount(BankAccount.builder().id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit()).expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit()).build())
				.build();

		Mockito.when(customerRepository.findById(this.customer.getId())).thenReturn(Optional.of(domain));

		Customer ex = customerService.addingBalance(customer.getId(), additionNumber);

		domain.addingBalance(additionNumber);
		assertEquals(domain.getBankAccount().getRemainAmount(), ex.getBankAccount().getRemainAmount());
	}
	
	@Test
	void test_add_customer_amount_not_exist() {
 		Double additionNumber = 10d;
 		Customer ex = customerService.addingBalance(customer.getId(), additionNumber);
		assertEquals(null, ex);
	}
}
