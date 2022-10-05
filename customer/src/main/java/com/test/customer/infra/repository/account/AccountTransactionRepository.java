package com.test.customer.infra.repository.account;

import java.time.LocalDateTime;
import java.util.List;

import com.test.customer.domain.AccountTransaction;

public interface AccountTransactionRepository {
	
	public List<AccountTransaction> findTransactionnByPeirod(LocalDateTime startDate, LocalDateTime endDate);
	
	public void addTransaction(AccountTransaction accountTransaction);
}
