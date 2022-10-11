package com.test.retail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

	private Integer id;

	private String code;

	private String name;

	private double unitPrice;

	private int remainNumber;
	
	private String description;

	public void increaseProductNumber(int productNumber) {
		this.remainNumber += productNumber;
	}
}
