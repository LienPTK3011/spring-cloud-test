package com.test.sale.app;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sale.domain.SaleOrder;
import com.test.sale.domain.SaleOrderDetail;
import com.test.sale.infra.repository.SaleOrderRepository;
import com.test.sale.ws.dto.SaleOrderDTO;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {

	@Autowired
	private SaleOrderRepository saleOrderRepository;

	@Override
	public void createSaleOrder(SaleOrderDTO dto) {
		this.saleOrderRepository.save(
			SaleOrder.builder()
				.customerId(dto.getCustomerId())
				.saleOrderDetails(dto.getLstOrderDetail().stream()
					.map(t -> SaleOrderDetail.builder()
						.productId(t.getProductId())
						.productCount(t.getProductId())
						.build()
					).collect(Collectors.toList())
				).build()
		);
	}

}
