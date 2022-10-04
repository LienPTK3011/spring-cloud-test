package com.test.sale.ws.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderDTO {
	private int customerId;
	private List<SaleOrderDetailDTO> lstOrderDetail;
}
