package com.test.retail.infra.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.test.retail.domain.Product;

public interface ProductRepository {
	
	public void save(Product product);
	
	public Optional<Product> findProductById(int productId);
	
	public List<Product> findByIds(Set<Integer> ids);
}
