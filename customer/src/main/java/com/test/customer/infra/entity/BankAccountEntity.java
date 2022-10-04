package com.test.customer.infra.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_account")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "bank_account_number")
	private String bankAccountNumber;

	@Column(name = "card_limit")
	private Double cardLimit;

	@Column(name = "exppired_date")
	private LocalDate expiredDate;

	@Column(name = "remain_amount")
	private Double remainAmount;
}
