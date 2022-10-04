package com.test.sale.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sale-order")
public class SaleOrderController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("demo")
	public String demo() {
		String demo = restTemplate.getForObject("http://customer-service/customer/demo", String.class);

		return demo;
	}
}
