package com.test.customer.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BankAccountDTO {

	private Integer id;

	private String bankAccountNumber;

	private Double cardLimit;

}
