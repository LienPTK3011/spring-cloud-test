package com.test.sale.ws.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SaleOrderDTO {
	private int customerId;
	private List<SaleOrderDetailDTO> lstOrderDetail;
}
