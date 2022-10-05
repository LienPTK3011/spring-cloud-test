package com.test.retail.app.external;

import feign.Param;
import feign.RequestLine;

public interface CustomerClientService {

	@RequestLine("GET /transaction/total-amount-by-date/{transaction-date}")
	public Double totalAmountByDate(@Param("transaction-date") String transactionDate);
}
