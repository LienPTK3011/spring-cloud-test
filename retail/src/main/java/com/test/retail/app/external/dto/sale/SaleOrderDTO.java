package com.test.retail.app.external.dto.sale;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderDTO {
	private int customerId;
	private List<SaleOrderDetailDTO> lstOrderDetail;
}
