package com.test.customer.infra.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.customer.domain.BankAccount;
import com.test.customer.domain.Customer;
import com.test.customer.domain.Gender;
import com.test.customer.infra.entity.BankAccountEntity;
import com.test.customer.infra.entity.CustomerEntity;
import com.test.customer.infra.jpa.JpaCustomerRepository;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private JpaCustomerRepository customerRepository;

	@Override
	public void update(Customer customer) {
		this.customerRepository.save(this.toEntity(customer));
	}

	@Override
	public Optional<Customer> findById(int customerId) {
		return this.customerRepository.findById(customerId).map(t -> this.toDomain(t));
	}

	private CustomerEntity toEntity(Customer customer) {
		return CustomerEntity.builder()
					.id(customer.getId())
					.name(customer.getName())
					.email(customer.getEmail())
					.birthday(customer.getBirthday())
					.gender(customer.getGender().value)
					.bankAccount(BankAccountEntity.builder()
							.id(customer.getBankAccount().getId())
							.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
							.cardLimit(customer.getBankAccount().getCardLimit())
							.expiredDate(customer.getBankAccount().getExpiredDate())
							.remainAmount(customer.getBankAccount().getRemainAmount())
							.build()
					).build();
	}
	
	private Customer toDomain(CustomerEntity customer) {
		return Customer.builder()
					.id(customer.getId())
					.name(customer.getName())
					.email(customer.getEmail())
					.birthday(customer.getBirthday())
					.gender(Gender.valueOf(customer.getGender()))
					.bankAccount(BankAccount.builder()
							.id(customer.getBankAccount().getId())
							.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
							.cardLimit(customer.getBankAccount().getCardLimit())
							.expiredDate(customer.getBankAccount().getExpiredDate())
							.remainAmount(customer.getBankAccount().getRemainAmount())
							.build()
					).build();
	}
}