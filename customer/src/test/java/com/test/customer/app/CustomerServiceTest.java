package com.test.customer.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
import com.test.customer.infra.jpa.JpaCustomerRepository;
import com.test.customer.infra.repository.CustomerRepository;
import com.test.customer.infra.repository.CustomerRepositoryImpl;
import com.test.customer.infra.repository.account.AccountTransactionRepository;
import com.test.customer.ws.dto.BankAccountDTO;
import com.test.customer.ws.dto.CustomerDTO;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();

	@InjectMocks
	CustomerRepository customerRepositoryMock = new CustomerRepositoryImpl();

	@Mock
	CustomerRepository customerRepository;

	@Mock
	JpaCustomerRepository jpaCustomerRepository;

	@Mock
	AccountTransactionRepository accountTransactionRepository;

	private CustomerDTO customer;

	@BeforeEach
	void setupData() {
		this.customer = CustomerDTO.builder().name("Trương Quang Hà").email("hatq@gmail.com").birthday("1995-11-30")
				.gender(Gender.MALE.value)
				.bankAccount(BankAccountDTO.builder().bankAccountNumber("1234567890").cardLimit(90000000d).build())
				.build();
	}

	@Test
	void customerInsert() {
		customerService.saveCustomer(this.customer);
	}

	@Test
	void test_reduce_customer_amount() {
		Double cardLimit = customer.getBankAccount().getCardLimit();
		Double reductionNnumber = 10d;
		Customer customerDomain = customerRepositoryMock.save(Customer.builder().name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(Gender.valueOf(customer.getGender()))
				.bankAccount(BankAccount.builder().id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit()).expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit()).build())
				.build());

		Customer ex = Mockito.verify(customerService).reduceCustomerDepositAmount(customerDomain.getId(),
				reductionNnumber);

		assertEquals((cardLimit - reductionNnumber), ex.getBankAccount().getRemainAmount());
	}

	@Test
	void test_add_customer_amount() {
		Double cardLimit = customer.getBankAccount().getCardLimit();
		Double reductionNnumber = 10d;
		Customer customerDomain = customerRepositoryMock.save(Customer.builder().name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(Gender.valueOf(customer.getGender()))
				.bankAccount(BankAccount.builder().id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit()).expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit()).build())
				.build());

		Customer ex = Mockito.verify(customerService).addingBalance(customerDomain.getId(), reductionNnumber);

		assertEquals((cardLimit + reductionNnumber), ex.getBankAccount().getRemainAmount());
	}
}
