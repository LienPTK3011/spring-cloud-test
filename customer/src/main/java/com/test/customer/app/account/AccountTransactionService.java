package com.test.customer.app.account;

import java.time.LocalDate;

public interface AccountTransactionService {

	/**
	 * Calculate amount by date
	 * 
	 * @param transactionDate
	 * @return: total amount by date
	 */
	public Double totalAmountByDate(LocalDate transactionDate);

}
