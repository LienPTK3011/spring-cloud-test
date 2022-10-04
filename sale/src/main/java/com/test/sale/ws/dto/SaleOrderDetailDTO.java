package com.test.sale.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderDetailDTO {
	private int productId;
	private int productCount;
}
