package com.test.sale.app;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sale.app.external.CustomerClientService;
import com.test.sale.app.external.ProductClientService;
import com.test.sale.app.external.queryparams.CustomerReduceBalanceDTO;
import com.test.sale.app.external.queryparams.ProductIncreaseDTO;
import com.test.sale.domain.SaleOrder;
import com.test.sale.domain.SaleOrderDetail;
import com.test.sale.infra.repository.SaleOrderRepository;
import com.test.sale.ws.dto.SaleOrderDTO;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {

	@Autowired
	private SaleOrderRepository saleOrderRepository;
	
	@Autowired
	private CustomerClientService customerClientService;

	@Autowired
	private ProductClientService productClientService;

	@Override
	public void createSaleOrder(SaleOrderDTO dto) {
		
		// save order
		SaleOrder saleOrder = this.saleOrderRepository.save(
			SaleOrder.builder()
				.customerId(dto.getCustomerId())
				.saleOrderDetails(dto.getLstOrderDetail().stream()
					.map(t -> SaleOrderDetail.builder()
						.productId(t.getProductId())
						.productCount(t.getProductId())
						.unitPrice(t.getUnitPrice())
						.build()
					).collect(Collectors.toList())
				).build()
		);
		
		// customer balance reduce
		this.customerClientService.reduceCustomerDepositAmount(
			new CustomerReduceBalanceDTO(dto.getCustomerId(), saleOrder.orderAmount())
		);

		// product number reduce
		for (SaleOrderDetail orderDetail: saleOrder.getSaleOrderDetails()) {
			this.productClientService.increaseProduct(
				new ProductIncreaseDTO(orderDetail.getProductId(), -orderDetail.getProductCount())
			);
		}
	}

}
