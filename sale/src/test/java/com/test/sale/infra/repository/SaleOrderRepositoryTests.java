package com.test.sale.infra.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.sale.infra.jpa.JpaSaleOrderDetailRepository;
import com.test.sale.infra.jpa.JpaSaleOrderRepository;

@ExtendWith(MockitoExtension.class)
public class SaleOrderRepositoryTests {
	
	@InjectMocks
	private SaleOrderRepository saleOrderRepository = new SaleOrderRepositoryImpl();
	
	@Mock
	private JpaSaleOrderRepository jpaSaleOrderRepository;
	
	@Mock
	private JpaSaleOrderDetailRepository saleOrderDetailRepository;

}
