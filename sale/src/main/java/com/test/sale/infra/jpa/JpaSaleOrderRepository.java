package com.test.sale.infra.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.sale.infra.entity.SaleOrderEntity;

public interface JpaSaleOrderRepository extends JpaRepository<SaleOrderEntity, Integer> {
	@Query("SELECT t FROM SaleOrderEntity t WHERE t.orderTime >= :startDate AND t.orderTime >= :endDate")
	public List<SaleOrderEntity> findByOrderDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
