package com.test.retail.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private String code;

	private String name;

	private double unitPrice;

	private int remainNumber;
	
	private String description;
}
