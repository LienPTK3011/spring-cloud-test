package com.test.retail.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.retail.infra.entity.SaleOrderEntity;

public interface SaleOrderRepository extends JpaRepository<SaleOrderEntity, Integer> {
}
