package com.test.sale.app;

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

import com.test.sale.app.external.CustomerClientService;
import com.test.sale.app.external.ProductClientService;
import com.test.sale.domain.SaleOrder;
import com.test.sale.domain.SaleOrderDetail;
import com.test.sale.infra.repository.SaleOrderRepository;
import com.test.sale.ws.dto.SaleOrderDTO;
import com.test.sale.ws.dto.SaleOrderDetailDTO;

@ExtendWith(MockitoExtension.class)
public class SaleOrderServiceTests {
	
	@InjectMocks
	private SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	
	@Mock
	private SaleOrderRepository saleOrderRepository;
	
	@Mock
	private CustomerClientService customerClientService;

	@Mock
	private ProductClientService productClientService;
	
	@Test
	void test_FindByDate() {
		List<SaleOrder> saleOrders = new ArrayList<>();
		List<SaleOrderDetail> dtos = new ArrayList<>();
		dtos.add(SaleOrderDetail.builder().productCount(1).productId(1).unitPrice(50d).build());
		saleOrders.add(SaleOrder.builder().customerId(1).saleOrderDetails(dtos).build());
		LocalDate transactionDate = LocalDate.now();
		// Start of transaction date
		LocalDateTime startDate = transactionDate.atTime(0, 0, 0);
		// End of transaction date
		LocalDateTime endDate = transactionDate.atTime(23, 59, 59);
		Mockito.when(saleOrderRepository.findByDate(startDate, endDate)).thenReturn(saleOrders);
		
		List<SaleOrderDTO> result = saleOrderService.findByDate(transactionDate);
		
		assertEquals(saleOrders.size(), result.size());
	}
	
	@Test
	void test_saveSaleOrder() {
		List<SaleOrderDetailDTO> dtos = new ArrayList<>();
		dtos.add(SaleOrderDetailDTO.builder().productCount(1).productId(1).unitPrice(50d).build());
		SaleOrderDTO dto = SaleOrderDTO.builder()
				.customerId(1)
				.lstOrderDetail(dtos)
				.build();
		
		saleOrderService.createSaleOrder(dto);
	}
}
