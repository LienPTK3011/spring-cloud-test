package com.test.sale.app.external;

import com.test.sale.app.external.queryparams.ProductIncreaseDTO;

import feign.RequestLine;

public interface ProductClientService {

	@RequestLine("POST /increase-product-number")
	public void increaseProduct(ProductIncreaseDTO dto);
}
