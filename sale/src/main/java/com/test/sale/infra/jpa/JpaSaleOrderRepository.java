package com.test.sale.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.sale.infra.entity.SaleOrderEntity;

public interface JpaSaleOrderRepository extends JpaRepository<SaleOrderEntity, Integer> {}
