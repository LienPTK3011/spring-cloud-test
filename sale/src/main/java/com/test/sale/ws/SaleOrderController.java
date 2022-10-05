package com.test.sale.ws;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.sale.app.SaleOrderService;
import com.test.sale.ws.dto.SaleOrderDTO;

@RestController
@RequestMapping("/sale-order")
public class SaleOrderController {
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private SaleOrderService saleOrderService;

	@PostMapping("/submit-order")
	public void submiOrder(@RequestBody SaleOrderDTO saleOrderDTO) {
		this.saleOrderService.createSaleOrder(saleOrderDTO);
	}
	
	@GetMapping("/find-by-date/{date}")
	public List<SaleOrderDTO> saleOrderLst(@PathVariable("date") String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return this.saleOrderService.findByDate(LocalDate.parse(date, formatter));
	}
}
