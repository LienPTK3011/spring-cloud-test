package com.test.sale.infra.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.test.sale.domain.SaleOrder;

public interface SaleOrderRepository {
	
	/**
	 * Save sale order 
	 *
	 * @param saleOrder: sale order info
	 */
	public SaleOrder save(SaleOrder saleOrder);
	
	public List<SaleOrder> findByDate(LocalDateTime startDate, LocalDateTime endDate);
}
