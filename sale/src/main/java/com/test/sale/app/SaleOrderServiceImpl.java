package com.test.sale.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import com.test.sale.ws.dto.SaleOrderDetailDTO;

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
		SaleOrder orderInfo = SaleOrder.builder()
				.customerId(dto.getCustomerId())
				.saleOrderDetails(dto.getLstOrderDetail().stream()
					.map(t -> SaleOrderDetail.builder()
						.productId(t.getProductId())
						.productCount(t.getProductId())
						.unitPrice(t.getUnitPrice())
						.build()
					).collect(Collectors.toList())
				).build();
		
		// save order
		this.saleOrderRepository.save(orderInfo);
		
		// customer balance reduce
		this.customerClientService.reduceCustomerDepositAmount(
			new CustomerReduceBalanceDTO(dto.getCustomerId(), orderInfo.orderAmount())
		);

		// product number reduce
		for (SaleOrderDetail orderDetail: orderInfo.getSaleOrderDetails()) {
			this.productClientService.increaseProduct(
				new ProductIncreaseDTO(orderDetail.getProductId(), -orderDetail.getProductCount())
			);
		}
	}

	@Override
	public List<SaleOrderDTO> findByDate(LocalDate date) {
		// Start of transaction date
		LocalDateTime startDate = date.atTime(0, 0, 0);
		// End of transaction date
		LocalDateTime endDate = date.atTime(23, 59, 59);
		
		return this.saleOrderRepository.findByDate(startDate, endDate)
					.stream()
					.map(t -> SaleOrderDTO.builder()
							.customerId(t.getCustomerId())
							.lstOrderDetail(
								t.getSaleOrderDetails().stream()
									.map(r -> SaleOrderDetailDTO.builder()
											.productId(r.getProductId())
											.productCount(r.getProductCount())
										.build()
									)
									.collect(Collectors.toList())
							)
							.build()
					)
					.collect(Collectors.toList());
	}

}
