package com.test.customer.ws;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.customer.app.CustomerService;
import com.test.customer.domain.Gender;
import com.test.customer.ws.dto.BankAccountDTO;
import com.test.customer.ws.dto.CustomerDTO;
import com.test.customer.ws.dto.CustomerReduceBalanceDTO;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	@Test
	public void testAddBalance() {
		CustomerReduceBalanceDTO dto = new CustomerReduceBalanceDTO(1, 100);
		customerController.addingBalance(dto);
	}

	@Test
	public void testReduceAmount() {
		CustomerReduceBalanceDTO dto = new CustomerReduceBalanceDTO(1, 100);
		customerController.reduceCustomerDepositAmount(dto);
	}
	
	@Test
	public void testCustomerSave() {
		CustomerDTO dto = CustomerDTO.builder()
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

		customerController.saveCustomer(dto);
	}
}
