package com.test.retail.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTests {
	private Product product;

	@BeforeEach
	void setupData() {
		this.product = Product.builder()
				.id(1)
				.code("123")
				.name("Demo")
				.description("adadasdas")
				.remainNumber(1000)
				.unitPrice(500).build();
	}
	
	@Test
	void tesst_builderr() {
		assertEquals(1, product.getId());
		assertEquals("123", product.getCode());
		assertEquals("Demo", product.getName());
		assertEquals("adadasdas", product.getDescription());
		assertEquals(1000, product.getRemainNumber());
		assertEquals(500, product.getUnitPrice());
		assertEquals(1, product.getId());
	}

	@Test
	void testIncreaseProductNumber() {

		// remain number
		double remain = this.product.getRemainNumber() + 10;

		product.increaseProductNumber(10);

		assertEquals(remain, product.getRemainNumber(), "product increase remain number");
	}

}
