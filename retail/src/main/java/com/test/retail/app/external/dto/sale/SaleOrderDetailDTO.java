package com.test.retail.app.external.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleOrderDetailDTO {
	private int productId;
	private int productCount;
}
