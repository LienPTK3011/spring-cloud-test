package com.test.sale.infra.repository;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.sale.domain.SaleOrder;
import com.test.sale.infra.entity.SaleOrderDetailEntity;
import com.test.sale.infra.entity.SaleOrderEntity;
import com.test.sale.infra.jpa.JpaSaleOrderRepository;

@Component
public class SaleOrderRepositoryImpl implements SaleOrderRepository {
	
	@Autowired
	private JpaSaleOrderRepository saleOrderRepository;

	@Override
	public void save(SaleOrder saleOrder) {
		this.saleOrderRepository.save(this.toEntity(saleOrder));
	}
	
	private SaleOrderEntity toEntity(SaleOrder saleOrder) {
		return SaleOrderEntity.builder()
					.customerId(saleOrder.getCustomerId())
					.orderTime(LocalDateTime.now())
					.saleOrderDetails(saleOrder.getSaleOrderDetails().stream()
						.map(t -> SaleOrderDetailEntity.builder()
							.productId(t.getProductId())
							.productCount(t.getProductCount())
							.build()
						).collect(Collectors.toList())
					)
					.build();
	}

}
