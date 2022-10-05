package com.test.customer.app.account;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.customer.domain.AccountTransaction;
import com.test.customer.infra.repository.account.AccountTransactionRepository;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

	@Autowired
	private AccountTransactionRepository accountTransactionRepository;

	@Override
	public Double totalAmountByDate(LocalDate transactionDate) {
		// Start of transaction date
		LocalDateTime startDate = transactionDate.atTime(0, 0, 0);
		// End of transaction date
		LocalDateTime endDate = transactionDate.atTime(23, 59, 59);
		
		// Calculate all transaction amount of date
		return this.accountTransactionRepository.findTransactionnByPeirod(startDate, endDate)
					.stream()
					.mapToDouble(AccountTransaction::getAmount)
					.sum();
	}
	
	
}
