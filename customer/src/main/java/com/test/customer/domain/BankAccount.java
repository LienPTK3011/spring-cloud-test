package com.test.customer.domain;

import java.time.LocalDate;

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
public class BankAccount {

	private int id;

	private String bankAccountNumber;

	private Double cardLimit;

	private LocalDate expiredDate;

	private Double remainAmount;
}
