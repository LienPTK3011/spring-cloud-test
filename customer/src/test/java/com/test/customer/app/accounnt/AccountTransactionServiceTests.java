package com.test.customer.app.accounnt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.customer.app.account.AccountTransactionService;
import com.test.customer.app.account.AccountTransactionServiceImpl;
import com.test.customer.domain.AccountTransaction;
import com.test.customer.infra.repository.account.AccountTransactionRepository;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionServiceTests {

	@InjectMocks
	AccountTransactionService service = new AccountTransactionServiceImpl();
	
	@Mock
	AccountTransactionRepository repository;
	
	@Test
	void testCalTotalAmountByDate() {
		Double amountResult = 10d;
		LocalDate transactionDate = LocalDate.now();
		// Start of transaction date
		LocalDateTime startDate = transactionDate.atTime(0, 0, 0);
		// End of transaction date
		LocalDateTime endDate = transactionDate.atTime(23, 59, 59);
		List<AccountTransaction> resutl = new ArrayList<AccountTransaction>();
		resutl.add(AccountTransaction.builder().amount(amountResult).transactionDate(LocalDateTime.now()).build());
		Mockito.when(repository.findTransactionnByPeirod(startDate, endDate)).thenReturn(resutl);
		
		Double totalAmount = service.totalAmountByDate(transactionDate);
		
		assertEquals(amountResult, totalAmount);
	}
}
