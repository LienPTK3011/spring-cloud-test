package com.test.sale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SaleOrderDetail {

	private int productId;

	private int productCount;

	private double unitPrice;

}
