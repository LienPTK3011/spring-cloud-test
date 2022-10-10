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
public class BankAccountDTO {

	private Integer id;

	private String bankAccountNumber;

	private Double cardLimit;

}
