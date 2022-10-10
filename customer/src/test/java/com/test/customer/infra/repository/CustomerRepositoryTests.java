package com.test.customer.infra.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.customer.infra.jpa.JpaCustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTests {

	@InjectMocks
	CustomerRepository customerRepository = new CustomerRepositoryImpl();
	
	@Mock
	JpaCustomerRepository jpaCustomerRepository;
}
