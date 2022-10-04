package com.test.sale.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder {

	private int customerId;

	private List<SaleOrderDetail> saleOrderDetails;
	
	public Double orderAmount() {
		return this.saleOrderDetails.stream()
				.mapToDouble(t -> t.getProductCount() * t.getUnitPrice())
				.sum();
	}
}
