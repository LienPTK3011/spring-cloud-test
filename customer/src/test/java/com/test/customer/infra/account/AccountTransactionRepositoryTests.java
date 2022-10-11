package com.test.customer.infra.account;

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

import com.test.customer.domain.AccountTransaction;
import com.test.customer.domain.BankAccount;
import com.test.customer.infra.entity.AccountTransactionEntity;
import com.test.customer.infra.jpa.JpaAccountTransactionRepository;
import com.test.customer.infra.repository.account.AccountTransactionRepository;
import com.test.customer.infra.repository.account.AccountTransactionRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionRepositoryTests {

	@InjectMocks
	AccountTransactionRepository accountTransactionRepository = new AccountTransactionRepositoryImpl();
	
	@Mock
	JpaAccountTransactionRepository jpaAccountTransactionRepository;
	
	@Test
	void tesFindList() {
		
		AccountTransactionEntity accountTransactionEntity = AccountTransactionEntity.builder().amount(50d).build();
		List<AccountTransactionEntity> accountTransactionEntities = new ArrayList<>();
		accountTransactionEntities.add(accountTransactionEntity);
		
		LocalDate transactionDate = LocalDate.now();
		// Start of transaction date
		LocalDateTime startDate = transactionDate.atTime(0, 0, 0);
		// End of transaction date
		LocalDateTime endDate = transactionDate.atTime(23, 59, 59);

		Mockito.when(jpaAccountTransactionRepository.findByDatePeriod(startDate, endDate)).thenReturn(accountTransactionEntities);
		
		assertEquals(accountTransactionEntities.size(), accountTransactionRepository.findTransactionnByPeirod(startDate, endDate).size());	
	
	}
	
	@Test
	void test_save() {
		AccountTransaction accountTransaction = AccountTransaction.builder().amount(50d)
				.transactionDate(LocalDateTime.now()).bankAccount(BankAccount.builder().id(1).build()).build();
		accountTransactionRepository.addTransaction(accountTransaction);
	}
}
