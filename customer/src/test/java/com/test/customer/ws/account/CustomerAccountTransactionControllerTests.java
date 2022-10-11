package com.test.customer.ws.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.customer.app.account.AccountTransactionService;
import com.test.customer.helper.DateHelper;

@ExtendWith(MockitoExtension.class)
public class CustomerAccountTransactionControllerTests {
	@InjectMocks
	CustomerAccountTransactionController controller;
	@Mock
	AccountTransactionService service;
	
	@Test
	void customerBalanceAmount() {
		Mockito.when(service.totalAmountByDate(LocalDate.now())).thenReturn(10d);
		
		assertEquals(10d, controller.totalAmountByDate(DateHelper.convertLocalDateToString(LocalDate.now(), DateHelper.DATE_FORMAT)));
	}

}
