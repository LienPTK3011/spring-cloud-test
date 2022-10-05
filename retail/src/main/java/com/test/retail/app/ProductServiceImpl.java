package com.test.retail.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.retail.domain.Product;
import com.test.retail.infra.repository.ProductRepository;
import com.test.retail.ws.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void increaseProduct(int productId, int productNumber) {
		Optional<Product> oProduct = this.productRepository.findProductById(productId);
		
		if (oProduct.isPresent()) {
			// increase product number
			oProduct.get().increaseProductNumber(productNumber);
			
			// update product
			this.productRepository.save(oProduct.get());
		}
	}

	@Override
	public void saveProduct(ProductDTO productDTO) {
		// add product
		this.productRepository.save(
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
