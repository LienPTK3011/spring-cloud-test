package com.test.retail.infra.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.retail.domain.Product;
import com.test.retail.infra.entity.ProductEntity;
import com.test.retail.infra.jpa.JpaProductRepository;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private JpaProductRepository productRepository;

	@Override
	public void save(Product product) {
		this.productRepository.save(this.toEntity(product));
	}

	@Override
	public Optional<Product> findProductById(int productId) {
		return this.productRepository.findById(productId).map(t -> this.toDomain(t));
	}

	@Override
	public List<Product> findByIds(Set<Integer> ids) {
		return this.productRepository.findByIdIn(ids)
				.stream()
				.map(t -> this.toDomain(t))
				.collect(Collectors.toList());
	}
	
	private ProductEntity toEntity(Product product) {
		return ProductEntity.builder()
					.id(product.getId())
					.code(product.getCode())
					.name(product.getName())
					.description(product.getDescription())
					.remainNumber(product.getRemainNumber())
					.unitPrice(product.getUnitPrice())
					.build();
	}
	
	private Product toDomain(ProductEntity product) {
		return Product.builder()
					  .id(product.getId())
					  .code(product.getCode())
					  .name(product.getName())
					  .description(product.getDescription())
					  .remainNumber(product.getRemainNumber())
					  .unitPrice(product.getUnitPrice())
					  .build();
	}
}
