package com.test.customer.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BankAccount {

	private Integer id;

	private String bankAccountNumber;

	private Double cardLimit;

	private LocalDate expiredDate;

	private Double remainAmount;
}
