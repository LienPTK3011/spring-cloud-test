package com.test.customer.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.customer.app.CustomerService;
import com.test.customer.ws.dto.CustomerDTO;
import com.test.customer.ws.dto.CustomerReduceBalanceDTO;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/reduce-customer-amount")
	public void reduceCustomerDepositAmount(@RequestBody CustomerReduceBalanceDTO balanceDTO) {
		this.customerService.reduceCustomerDepositAmount(
			balanceDTO.getCustomerId(),
			balanceDTO.getAmount()
		);
	}
	
	@PostMapping("/add-balance")
	public void addingBalance(@RequestBody CustomerReduceBalanceDTO balanceDTO) {
		this.customerService.addingBalance(
			balanceDTO.getCustomerId(),
			balanceDTO.getAmount()
		);
	}
	
	@PostMapping("/save-customer")
	public void saveCustomer(@RequestBody CustomerDTO customer) {
		this.customerService.saveCustomer(customer);
	}
}
