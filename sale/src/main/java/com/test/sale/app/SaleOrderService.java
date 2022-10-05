package com.test.sale.app;

import java.time.LocalDate;
import java.util.List;

import com.test.sale.ws.dto.SaleOrderDTO;

public interface SaleOrderService {

	/**
	 * Customer purchase product
	 * 
	 * @param dto: sale order info
	 */
	public void createSaleOrder(SaleOrderDTO dto);
	
	public List<SaleOrderDTO> findByDate(LocalDate date);
}
