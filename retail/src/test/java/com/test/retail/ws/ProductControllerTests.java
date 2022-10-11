package com.test.retail.ws;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.retail.app.ProductService;
import com.test.retail.ws.dto.ProductDTO;
import com.test.retail.ws.dto.ProductIncreaseDTO;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService service;
	
	@Test
	void test_product_increase() {
		ProductIncreaseDTO dto = new ProductIncreaseDTO(1, 10);
		productController.increaseProduct(dto);
		
		Mockito.verify(service).increaseProduct(dto.getProductId(), dto.getProductNumber());
	}
	
	@Test
	void test_product_save() {
		ProductDTO productDTO = new ProductDTO("001", "Demo", 50d, 20, "Demo");
		productController.save(productDTO);
		
		Mockito.verify(service).saveProduct(productDTO);
	}
}
