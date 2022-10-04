package com.test.customer.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.customer.domain.Customer;
import com.test.customer.infra.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void reduceCustomerDepositAmount(int customerId, double amount) {
		// Find customer by ID
		Optional<Customer> oCustomer = this.customerRepository.findById(customerId);

		if (oCustomer.isPresent()) {
			// Reduce customer amount
			oCustomer.get().reduceCustomerAmount(amount);

			// update customer
			this.customerRepository.update(oCustomer.get());
		}
	}

}
