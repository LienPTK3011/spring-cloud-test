package com.test.retail.app;

import com.test.retail.ws.dto.ProductDTO;

public interface ProductService {

	/**
	 * Increase product number in inventory
	 * 
	 * @param productId: product id
	 * @param productNumber: number of product will be added to inventory
	 */
	public void increaseProduct(int productId, int productNumber);
	
	/**
	 * Save product
	 * 
	 * @param productDTO
	 */
	public void saveProduct(ProductDTO productDTO);
}
