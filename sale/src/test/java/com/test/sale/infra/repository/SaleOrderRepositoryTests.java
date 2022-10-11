package com.test.sale.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.sale.domain.SaleOrder;
import com.test.sale.domain.SaleOrderDetail;
import com.test.sale.infra.entity.SaleOrderDetailEntity;
import com.test.sale.infra.entity.SaleOrderEntity;
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
	
	private SaleOrder saleOrder;
	
	private List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		saleOrderDetails.add(SaleOrderDetail.builder().productCount(1).productId(1).unitPrice(50d).build());
		saleOrder = SaleOrder.builder()
				.customerId(1)
				.saleOrderDetails(saleOrderDetails)
				.build();
	}
	
	@Test
	void test_save()  {
		List<SaleOrderDetailEntity> saleOrderDetailEntities = new ArrayList<SaleOrderDetailEntity>();
		saleOrderDetailEntities.add(SaleOrderDetailEntity.builder().productId(1).productCount(1).build());

		SaleOrderEntity saleOrderMock  = SaleOrderEntity.builder()
				.id(1)
				.customerId(1)
				.orderTime(LocalDateTime.now())
				.build();

		Mockito.lenient().when(this.jpaSaleOrderRepository.saveAndFlush(
			SaleOrderEntity.builder()
				.customerId(saleOrder.getCustomerId())
				.orderTime(LocalDateTime.now())
				.build()
		)).thenReturn(saleOrderMock);
		
		saleOrderRepository.save(saleOrder);

	}
	
	@Test
	void test_findByDate() {
		List<SaleOrderEntity> saleOrders = new ArrayList<>();
		List<SaleOrderDetailEntity> dtos = new ArrayList<>();
		dtos.add(SaleOrderDetailEntity.builder().productCount(1).productId(1).build());
		saleOrders.add(SaleOrderEntity.builder().customerId(1).saleOrderDetails(dtos).build());
		LocalDate transactionDate = LocalDate.now();
		// Start of transaction date
		LocalDateTime startDate = transactionDate.atTime(0, 0, 0);
		// End of transaction date
		LocalDateTime endDate = transactionDate.atTime(23, 59, 59);
		Mockito.when(jpaSaleOrderRepository.findByOrderDate(startDate, endDate)).thenReturn(saleOrders);
		
		List<SaleOrder> result = saleOrderRepository.findByDate(startDate, endDate);
		
		assertEquals(saleOrders.size(), result.size());
	}
	
	

}
