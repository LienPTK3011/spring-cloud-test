package com.test.customer.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.customer.domain.AccountTransaction;
import com.test.customer.domain.BankAccount;
import com.test.customer.domain.Customer;
import com.test.customer.domain.Gender;
import com.test.customer.helper.DateHelper;
import com.test.customer.infra.repository.CustomerRepository;
import com.test.customer.infra.repository.account.AccountTransactionRepository;
import com.test.customer.ws.dto.BankAccountDTO;
import com.test.customer.ws.dto.CustomerDTO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountTransactionRepository accountTransactionRepository;

	@Override
	public Customer reduceCustomerDepositAmount(int customerId, double amount) {
		// Find customer by ID
		Optional<Customer> oCustomer = this.customerRepository.findById(customerId);

		if (oCustomer.isPresent()) {
			// Reduce customer amount
			oCustomer.get().reduceCustomerAmount(amount);

			// update customer
			Customer result = this.customerRepository.save(oCustomer.get());
			
			this.accountTransactionRepository.addTransaction(
					AccountTransaction.builder()
						.amount(amount)
						.transactionDate(LocalDateTime.now())
						.bankAccount(oCustomer.get().getBankAccount())
						.build()
			);
			
			return result;

		}
		return null;
	}

	@Override
	public Customer addingBalance(int customerId, double amount) {
		// Find customer by ID
		Optional<Customer> oCustomer = this.customerRepository.findById(customerId);

		if (oCustomer.isPresent()) {
			// Reduce customer amount
			oCustomer.get().addingBalance(amount);

			// update customer
			return this.customerRepository.save(oCustomer.get());
		}
		return null;
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer customer = this.customerRepository.save(this.toDomain(customerDTO));
		return this.toDTO(customer);
	}
	
	private Customer toDomain(CustomerDTO customerDTO) {
		return Customer.builder()
				.birthday(DateHelper.convertStringToLocalDate(customerDTO.getBirthday(), DateHelper.DATE_FORMAT))
				.name(customerDTO.getName())
				.gender(Gender.valueOf(customerDTO.getGender()))
				.email(customerDTO.getEmail())
				.bankAccount(
					BankAccount.builder()
						.bankAccountNumber(customerDTO.getBankAccount().getBankAccountNumber())
						.cardLimit(customerDTO.getBankAccount().getCardLimit())
						.expiredDate(LocalDate.now().plusYears(5))
						.remainAmount(customerDTO.getBankAccount().getCardLimit())
						.build()
				)
				.build();
	}

	private CustomerDTO toDTO(Customer customer) {
		return CustomerDTO.builder()
				.birthday(DateHelper.convertLocalDateToString(customer.getBirthday(), DateHelper.DATE_FORMAT))
				.name(customer.getName())
				.gender(customer.getGender().value)
				.email(customer.getEmail())
				.bankAccount(
					BankAccountDTO.builder()
						.bankAccountNumber(customer.getBankAccount().getBankAccountNumber())
						.cardLimit(customer.getBankAccount().getCardLimit())
						.build()
				)
				.build();
	}

}
