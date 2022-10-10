package com.test.retail.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTests {
	private Product product;

	@BeforeEach
	void setupData() {
		this.product = Product.builder().code("123").name("Demo").description("adadasdas").remainNumber(1000)
				.unitPrice(500).build();
	}

	@Test
	void testIncreaseProductNumber() {

		// remain number
		double remain = this.product.getRemainNumber() + 10;

		product.increaseProductNumber(10);

		assertEquals(remain, product.getRemainNumber(), "product increase remain number");
	}

}
