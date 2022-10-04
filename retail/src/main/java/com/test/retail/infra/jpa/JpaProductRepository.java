package com.test.retail.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.retail.infra.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {}
