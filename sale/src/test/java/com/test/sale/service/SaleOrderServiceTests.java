package com.test.sale.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.sale.app.SaleOrderService;
import com.test.sale.app.SaleOrderServiceImpl;
import com.test.sale.app.external.CustomerClientService;
import com.test.sale.app.external.ProductClientService;
import com.test.sale.infra.jpa.JpaSaleOrderDetailRepository;
import com.test.sale.infra.jpa.JpaSaleOrderRepository;
import com.test.sale.infra.repository.SaleOrderRepository;
import com.test.sale.infra.repository.SaleOrderRepositoryImpl;
import com.test.sale.ws.dto.SaleOrderDTO;
import com.test.sale.ws.dto.SaleOrderDetailDTO;

@ExtendWith(MockitoExtension.class)
public class SaleOrderServiceTests {

	@InjectMocks
    private SaleOrderService service = new SaleOrderServiceImpl();
	
	@InjectMocks
	private SaleOrderRepository saleOrderRepository = new SaleOrderRepositoryImpl();

	@Mock
	private JpaSaleOrderRepository jpaSaleOrderRepository;
	
	@Mock
	private JpaSaleOrderDetailRepository saleOrderDetailRepository;
	@Mock
	private CustomerClientService customerService;
	
	@Mock
	private ProductClientService productService;
	
	@Test
	void testCreateSaleOrder() {
		List<SaleOrderDetailDTO> saleOrderDetailDTOs = new ArrayList<SaleOrderDetailDTO>();
		saleOrderDetailDTOs.add(SaleOrderDetailDTO.builder().productId(1).productCount(1).unitPrice(20).build());

		SaleOrderDTO saleOrderDTO  = SaleOrderDTO.builder()
				.customerId(1)
				.lstOrderDetail(saleOrderDetailDTOs)
				.build();
		
		service.createSaleOrder(saleOrderDTO);
	}

}
