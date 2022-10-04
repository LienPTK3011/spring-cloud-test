package com.test.customer.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.customer.app.CustomerService;
import com.test.customer.ws.dto.CustomerReduceBalanceDTO;

@RestController("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/reduce-customer-amount")
	public void reduceCustomerDepositAmount(CustomerReduceBalanceDTO balanceDTO) {

		this.customerService.reduceCustomerDepositAmount(
			balanceDTO.getCustomerId(),
			balanceDTO.getReductionAmount()
		);
	}

}
