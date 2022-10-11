package com.test.customer.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustomerDTO {

	private Integer id;

	private String name;

	private String birthday;

	private Integer gender;

	private String email;
	
	private BankAccountDTO bankAccount;
}
