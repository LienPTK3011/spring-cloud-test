package com.test.retail.app.external.dto.sale;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleOrderDTO {
	private int customerId;
	private List<SaleOrderDetailDTO> lstOrderDetail;
}
