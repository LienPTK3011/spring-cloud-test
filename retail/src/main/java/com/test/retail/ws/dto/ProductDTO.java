package com.test.retail.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {

	private String code;

	private String name;

	private double unitPrice;

	private int remainNumber;
	
	private String description;
}
