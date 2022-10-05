package com.test.retail.infra.jpa;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.retail.infra.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {
	public List<ProductEntity> findByIdIn(Set<Integer> ids);
}
