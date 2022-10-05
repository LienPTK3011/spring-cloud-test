package com.test.retail.app.external.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderDetailDTO {
	private int productId;
	private int productCount;
}
