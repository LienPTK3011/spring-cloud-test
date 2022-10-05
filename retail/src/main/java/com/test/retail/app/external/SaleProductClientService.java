package com.test.retail.app.external;

import java.util.List;

import com.test.retail.app.external.dto.sale.SaleOrderDTO;

import feign.Param;
import feign.RequestLine;

public interface SaleProductClientService {

	@RequestLine("GET /find-by-date/{date}")
	public List<SaleOrderDTO> saleOrderLst(@Param("date") String date);
}
