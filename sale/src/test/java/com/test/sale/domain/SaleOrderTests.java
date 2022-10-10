package com.test.sale.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaleOrderTests {
	
	private SaleOrder saleOrder;
	
	@BeforeEach
	void beforeEach() {
		List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();
		saleOrderDetails.add(SaleOrderDetail.builder().productCount(1).productId(1).unitPrice(50).build());
		saleOrder = SaleOrder.builder().customerId(1).saleOrderDetails(saleOrderDetails).build();
	}
	
	@Test
	void testCalculateOderAmount() {
		Double orderAmount = saleOrder.getSaleOrderDetails().stream().mapToDouble(t -> t.getProductCount()* t.getUnitPrice()).sum();
		assertEquals(orderAmount, saleOrder.orderAmount());
	}

}
