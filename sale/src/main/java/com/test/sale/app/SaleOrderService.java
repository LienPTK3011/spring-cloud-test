package com.test.sale.app;

import com.test.sale.ws.dto.SaleOrderDTO;

public interface SaleOrderService {

	/**
	 * Customer purchase product
	 * 
	 * @param dto: sale order info
	 */
	public void createSaleOrder(SaleOrderDTO dto);
}
