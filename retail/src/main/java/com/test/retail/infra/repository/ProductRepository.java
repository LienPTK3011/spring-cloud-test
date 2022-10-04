package com.test.retail.infra.repository;

import java.util.Optional;

import com.test.retail.domain.Product;

public interface ProductRepository {
	
	public void update(Product product);
	
	public Optional<Product> findProductById(int productId);
}
