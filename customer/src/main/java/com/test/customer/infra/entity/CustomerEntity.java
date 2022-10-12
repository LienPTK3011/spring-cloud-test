package com.test.customer.infra.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "birthday")
	private LocalDate birthday;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "email")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_account_id", referencedColumnName = "id")
	private BankAccountEntity bankAccount;

	@Override
	public boolean equals(Object obj) {
		CustomerEntity entity = (CustomerEntity) obj;
		return this.id == entity.getId();
	}
}
