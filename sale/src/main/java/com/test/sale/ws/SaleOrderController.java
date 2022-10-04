package com.test.sale.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.sale.app.SaleOrderService;
import com.test.sale.ws.dto.SaleOrderDTO;

@RestController
@RequestMapping("/sale-order")
public class SaleOrderController {

	@Autowired
	private SaleOrderService saleOrderService;

	@PostMapping("/submit-order")
	public void submiOrder(SaleOrderDTO saleOrderDTO) {
		this.saleOrderService.createSaleOrder(saleOrderDTO);
	}
}
