package com.test.customer.ws.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.customer.app.account.AccountTransactionService;
import com.test.customer.helper.DateHelper;

@RestController
@RequestMapping("/customer/transaction")
public class CustomerAccountTransactionController {

	@Autowired
	private AccountTransactionService accountTransactionService;
	
	@GetMapping("/total-amount-by-date/{transaction-date}")
	public Double totalAmountByDate(@PathVariable("transaction-date") String transactionDate) {
		return this.accountTransactionService
				.totalAmountByDate(DateHelper.convertStringToLocalDate(transactionDate, DateHelper.DATE_FORMAT));
	}
}
