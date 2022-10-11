package com.test.retail.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.retail.domain.Product;
import com.test.retail.infra.entity.ProductEntity;
import com.test.retail.infra.jpa.JpaProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTests {

	@InjectMocks
	ProductRepository productRepository = new ProductRepositoryImpl();
	
	@Mock
	JpaProductRepository jpaProductRepository;
	
	@Test
	void test_save() {
		Product product = new Product(null, "001", "Demo", 50d, 20, "Demo");
		ProductEntity entity = new ProductEntity(null, "001", "Demo", 50d, 20, "Demo");
		
		productRepository.save(product);
		
		Mockito.verify(jpaProductRepository).save(entity);
	}
	
	@Test
	void test_find_product() {
		int productId = 1;
		Product product = new Product(productId, "001", "Demo", 50d, 20, "Demo");
		ProductEntity entity = new ProductEntity(productId, "001", "Demo", 50d, 20, "Demo");
		
		Mockito.when(jpaProductRepository.findById(productId)).thenReturn(Optional.of(entity));
		
		assertEquals(product, productRepository.findProductById(productId).get());
	}
	
	@Test
	void test_find_product_by_ids() {
		int productId = 1;
		Set<Integer> productIds = Arrays.asList(productId).stream().collect(Collectors.toSet());
		List<Product> products =  Arrays.asList(new Product(productId, "001", "Demo", 50d, 20, "Demo"));
		List<ProductEntity> entitys = Arrays.asList(new ProductEntity(productId, "001", "Demo", 50d, 20, "Demo"));
		
		Mockito.when(jpaProductRepository.findByIdIn(productIds)).thenReturn(entitys);
		
		assertEquals(products.size(), productRepository.findByIds(productIds).size());
	}
}
