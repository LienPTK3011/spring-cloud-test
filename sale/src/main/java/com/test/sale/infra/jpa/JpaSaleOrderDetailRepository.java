package com.test.sale.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.sale.infra.entity.SaleOrderDetailEntity;

public interface JpaSaleOrderDetailRepository extends JpaRepository<SaleOrderDetailEntity, Integer> {
}
