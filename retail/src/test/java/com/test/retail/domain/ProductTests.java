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
	void tesst_builder() {
		Product product = Product.builder()
							.id(null)
							.code(null)
							.name(null)
							.description(null)
							.remainNumber(100)
							.unitPrice(50)
							.build();
		assertEquals(null, product.getCode());
	}

	@Test
	void testIncreaseProductNumber() {

		// remain number
		double remain = this.product.getRemainNumber() + 10;

		product.increaseProductNumber(10);

		assertEquals(remain, product.getRemainNumber(), "product increase remain number");
	}

}
