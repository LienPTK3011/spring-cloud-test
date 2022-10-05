package com.test.customer.infra.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.customer.infra.entity.AccountTransactionEntity;

public interface JpaAccountTransactionRepository extends JpaRepository<AccountTransactionEntity, Integer> {

	@Query("SELECT t FROM AccountTransactionEntity t WHERE t.transactionDate >= :startDate AND t.transactionDate <= :endDate")
	List<AccountTransactionEntity> findByDatePeriod(@Param("startDate") LocalDateTime startDate,
													@Param("endDate") LocalDateTime endDate);

}
