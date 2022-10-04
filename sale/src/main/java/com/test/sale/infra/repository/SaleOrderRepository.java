package com.test.sale.infra.repository;

import com.test.sale.domain.SaleOrder;

public interface SaleOrderRepository {
	
	/**
	 * Save sale order 
	 *
	 * @param saleOrder: sale order info
	 */
	public SaleOrder save(SaleOrder saleOrder);
}
