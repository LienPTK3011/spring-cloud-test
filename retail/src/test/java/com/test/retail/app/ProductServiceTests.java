package com.test.retail.app;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.retail.domain.Product;
import com.test.retail.infra.repository.ProductRepository;
import com.test.retail.ws.dto.ProductDTO;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

	@InjectMocks
	ProductService productService = new ProductServiceImpl();

	@Mock
	private ProductRepository productRepository;
	
	@Test
	void test_increaseProduct() {
		int productId = 1;
		Product product = new Product(productId, "001", "Demo", 50d, 20, "Demo");
		
		Mockito.when(productRepository.findProductById(productId)).thenReturn(Optional.of(product));
		
		int numberIncrease = 10;
		productService.increaseProduct(productId, numberIncrease);
		
		product.increaseProductNumber(numberIncrease);

		Mockito.verify(productRepository).save(product);
	}
	
	@Test
	void test_increaseProduct_product_not_exist() {
		int productId = 1;
		int numberIncrease = 10;
		Product product = new Product(1, "001", "Demo", 50d, 20, "Demo");
		productService.increaseProduct(productId, numberIncrease);
		
		product.increaseProductNumber(numberIncrease);
	}
	
	@Test
	void testSaveProduct() {
		ProductDTO productDTO = new ProductDTO("001", "Demo", 50d, 20, "Demo");

		productService.saveProduct(productDTO);
		
		Mockito.verify(productRepository).save(
			Product.builder()
				.code(productDTO.getCode())
				.name(productDTO.getName())
				.unitPrice(productDTO.getUnitPrice())
				.remainNumber(productDTO.getRemainNumber())
				.description(productDTO.getDescription())
				.build()
		);
	}
}
