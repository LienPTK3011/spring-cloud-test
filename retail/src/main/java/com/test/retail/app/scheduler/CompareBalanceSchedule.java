package com.test.retail.app.scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.test.retail.app.external.CustomerClientService;
import com.test.retail.app.external.SaleProductClientService;
import com.test.retail.app.external.dto.sale.SaleOrderDetailDTO;
import com.test.retail.domain.Product;
import com.test.retail.infra.repository.ProductRepository;

@Service
public class CompareBalanceSchedule {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerClientService customerClientService;

	@Autowired
	private SaleProductClientService saleProductClientService;

//	@Scheduled(cron = "0 0 0 * * *")
	@Scheduled(fixedDelay = 20000)
	public void compareBalance() {
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
		
		// total amount from customer deposit account
		Double customerDepositAmount = this.customerClientService.totalAmountByDate(today);
		
		List<SaleOrderDetailDTO> lstProductSolded = this.saleProductClientService.saleOrderLst(today)
				.stream()
				.flatMap(t -> t.getLstOrderDetail().stream())
				.collect(Collectors.toList());
		
		// Map product id with number product sold
		Map<Integer, Integer> mapProductCount = lstProductSolded.stream()
				.collect(Collectors.groupingBy(t -> t.getProductId(), Collectors.summingInt(r -> r.getProductCount())));
		
		List<Product> lstProductsSold = this.productRepository.findByIds(mapProductCount.keySet());
		
		//  total amount from sale order of today
		Double saleAmount = lstProductsSold.stream().mapToDouble(t -> t.getUnitPrice() * mapProductCount.get(t.getId())).sum();

		System.out.println(customerDepositAmount + "--------------" + saleAmount);
	}
}
