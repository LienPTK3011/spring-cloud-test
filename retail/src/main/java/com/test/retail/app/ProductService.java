package com.test.retail.app;

public interface ProductService {

	/**
	 * Increase product number in inventory
	 * 
	 * @param productId: product id
	 * @param productNumber: number of product will be added to inventory
	 */
	public void increaseProduct(int productId, int productNumber);
}
