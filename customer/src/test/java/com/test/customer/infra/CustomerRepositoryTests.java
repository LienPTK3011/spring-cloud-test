package com.test.customer.infra;

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
import com.test.customer.infra.entity.BankAccountEntity;
import com.test.customer.infra.entity.CustomerEntity;
import com.test.customer.infra.jpa.JpaCustomerRepository;
import com.test.customer.infra.repository.CustomerRepository;
import com.test.customer.infra.repository.CustomerRepositoryImpl;
import com.test.customer.ws.dto.BankAccountDTO;
import com.test.customer.ws.dto.CustomerDTO;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTests {

	@InjectMocks
	CustomerRepository customerRepository = new CustomerRepositoryImpl();

	@Mock
	JpaCustomerRepository jpaCustomerRepository;

	private CustomerDTO customer;

	@BeforeEach
	void setupData() {
		this.customer = CustomerDTO.builder().id(1).name("Trương Quang Hà").email("hatq@gmail.com")
				.birthday("1995-11-30").gender(Gender.MALE.value)
				.bankAccount(
						BankAccountDTO.builder().id(1).bankAccountNumber("1234567890").cardLimit(90000000d).build())
				.build();
	}

	@Test
	void testSaveCustomer() {
		CustomerEntity entity = CustomerEntity.builder()
				.id(customer.getId())
				.name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(customer.getGender())
				.bankAccount(BankAccountEntity.builder()
						.id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit())
						.expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit()).build())
				.build();
		
		CustomerEntity entityMock = entity;

		Mockito.lenient().when(jpaCustomerRepository.save(entity)).thenReturn(entityMock);

		Customer domain = Customer.builder()
				.id(customer.getId())
				.name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(Gender.valueOf(customer.getGender()))
				.bankAccount(BankAccount.builder()
						.id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit())
						.expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit())
						.build()
				).build();

		assertEquals(domain.getId(), customerRepository.save(domain).getId());

	}
	
	@Test
	void testFindCustomer() {
		CustomerEntity entity = CustomerEntity.builder().id(customer.getId()).name(customer.getName())
			.email(customer.getEmail())
			.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
			.gender(customer.getGender())
			.bankAccount(BankAccountEntity.builder().id(customer.getBankAccount().getId())
					.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
					.cardLimit(customer.getBankAccount().getCardLimit()).expiredDate(LocalDate.now().plusYears(5))
					.remainAmount(customer.getBankAccount().getCardLimit()).build())
			.build();
		
		Mockito.when(jpaCustomerRepository.findById(customer.getId())).thenReturn(Optional.of(entity));
		
		Customer domain = Customer.builder()
				.id(customer.getId())
				.name(customer.getName())
				.email(customer.getEmail())
				.birthday(DateHelper.convertStringToLocalDate(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.gender(Gender.valueOf(customer.getGender()))
				.bankAccount(BankAccount.builder()
						.id(customer.getBankAccount().getId())
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit())
						.expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customer.getBankAccount().getCardLimit())
						.build()
				).build();
		
		assertEquals(domain, customerRepository.findById(customer.getId()).get());

	}
}
