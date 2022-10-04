package com.test.retail.infra.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity	
@Table(name = "sale_order")
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "order_time")
	private LocalDateTime orderTime;

	@OneToMany(mappedBy = "saleOrder", cascade = CascadeType.ALL)
	private List<SaleOrderDetailEntity> saleOrderDetails;

}