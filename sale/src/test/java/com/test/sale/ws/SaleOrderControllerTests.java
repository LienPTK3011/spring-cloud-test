package com.test.sale.ws;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.sale.app.SaleOrderService;
import com.test.sale.ws.dto.SaleOrderDTO;
import com.test.sale.ws.dto.SaleOrderDetailDTO;

@ExtendWith(MockitoExtension.class)
public class SaleOrderControllerTests {

	@InjectMocks
	SaleOrderController saleOrderController;
	
	@Mock
	SaleOrderService saleOrderService;
	
	@Test
	void saveSaleOrrder() {
		List<SaleOrderDetailDTO> saleOrderDetailDTOs = new ArrayList<SaleOrderDetailDTO>();
		saleOrderDetailDTOs.add(SaleOrderDetailDTO.builder().productId(1).productCount(1).unitPrice(20).build());

		SaleOrderDTO saleOrderDTO  = SaleOrderDTO.builder()
				.customerId(1)
				.lstOrderDetail(saleOrderDetailDTOs)
				.build();
		saleOrderController.submiOrder(saleOrderDTO);
		Mockito.verify(saleOrderService).createSaleOrder(saleOrderDTO);
	}
	
	@Test
	void testSaleOrderLst() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		saleOrderController.saleOrderLst(LocalDate.now().format(formatter));
		Mockito.verify(saleOrderService).findByDate(LocalDate.now());
	}
}
