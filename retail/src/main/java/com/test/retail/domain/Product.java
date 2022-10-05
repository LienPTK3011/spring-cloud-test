package com.test.retail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
