package com.test.retail.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.retail.app.ProductService;
import com.test.retail.ws.dto.ProductIncreaseDTO;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping("/increase-product-number")
	public void increaseProduct(ProductIncreaseDTO dto) {
		this.productService.increaseProduct(dto.getProductId(), dto.getProductNumber());
	}

}
