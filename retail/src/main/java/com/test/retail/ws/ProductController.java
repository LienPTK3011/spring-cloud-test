package com.test.retail.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.retail.app.ProductService;
import com.test.retail.ws.dto.ProductDTO;
import com.test.retail.ws.dto.ProductIncreaseDTO;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping("/increase-product-number")
	public void increaseProduct(@RequestBody ProductIncreaseDTO dto) {
		this.productService.increaseProduct(dto.getProductId(), dto.getProductNumber());
	}

	@PostMapping("/save-product")
	public void save(@RequestBody ProductDTO dto) {
		this.productService.saveProduct(dto);
	}

}
