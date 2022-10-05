package com.test.sale.infra.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.sale.domain.SaleOrder;
import com.test.sale.domain.SaleOrderDetail;
import com.test.sale.infra.entity.SaleOrderDetailEntity;
import com.test.sale.infra.entity.SaleOrderEntity;
import com.test.sale.infra.jpa.JpaSaleOrderDetailRepository;
import com.test.sale.infra.jpa.JpaSaleOrderRepository;

@Component
public class SaleOrderRepositoryImpl implements SaleOrderRepository {
	
	@Autowired
	private JpaSaleOrderRepository saleOrderRepository;
	
	@Autowired
	private JpaSaleOrderDetailRepository saleOrderDetailRepository;

	@Override
	public SaleOrder save(SaleOrder saleOrder) {
		SaleOrderEntity entity = this.saleOrderRepository.saveAndFlush(
			SaleOrderEntity.builder()
			.customerId(saleOrder.getCustomerId())
			.orderTime(LocalDateTime.now())
			.build()
		);
		
		List<SaleOrderDetailEntity> saleOrderDetailEntities = saleOrder.getSaleOrderDetails().stream()
						.map(t -> SaleOrderDetailEntity.builder()
									.saleOrder(entity)
									.productId(t.getProductId())
									.productCount(t.getProductCount())
									.build()
						).collect(Collectors.toList());
		
		List<SaleOrderDetailEntity> savedDetail = this.saleOrderDetailRepository.saveAll(saleOrderDetailEntities);
		entity.setSaleOrderDetails(savedDetail);
		
		return this.toDomain(entity);
	}

	@Override
	public List<SaleOrder> findByDate(LocalDateTime startDate, LocalDateTime endDate) {
		return this.saleOrderRepository.findByOrderDate(startDate, endDate)
				.stream()
				.map(t -> this.toDomain(t))
				.collect(Collectors.toList());
	}
	
	private SaleOrder toDomain(SaleOrderEntity saleOrder) {
		return SaleOrder.builder()
					.customerId(saleOrder.getCustomerId())
					.saleOrderDetails(saleOrder.getSaleOrderDetails().stream()
						.map(t -> SaleOrderDetail.builder()
							.productId(t.getProductId())
							.productCount(t.getProductCount())
							.build()
						).collect(Collectors.toList())
					)
					.build();
	}

}
