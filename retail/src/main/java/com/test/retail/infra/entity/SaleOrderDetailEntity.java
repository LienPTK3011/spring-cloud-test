package com.test.retail.infra.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_order_detail")
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderDetailEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_count")
	private int productCount;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_order_id")
	private SaleOrderEntity saleOrder;
}
