package com.test.customer.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.customer.infra.entity.CustomerEntity;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Integer> {}
