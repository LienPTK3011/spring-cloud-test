package com.test.customer.ws.dto;

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
public class CustomerDTO {

	private Integer id;

	private String name;

	private String birthday;

	private Integer gender;

	private String email;
	
	private BankAccountDTO bankAccount;
}
