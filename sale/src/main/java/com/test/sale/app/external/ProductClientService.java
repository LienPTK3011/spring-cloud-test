package com.test.sale.app.external;

import com.test.sale.app.external.queryparams.ProductIncreaseDTO;

import feign.Headers;
import feign.RequestLine;

public interface ProductClientService {

	@RequestLine("POST /increase-product-number")
    @Headers("Content-Type: application/json")
	public void increaseProduct(ProductIncreaseDTO dto);
}
