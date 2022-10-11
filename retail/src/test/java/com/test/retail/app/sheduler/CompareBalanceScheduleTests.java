package com.test.retail.app.sheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.retail.app.external.CustomerClientService;
import com.test.retail.app.external.SaleProductClientService;
import com.test.retail.app.external.dto.sale.SaleOrderDTO;
import com.test.retail.app.external.dto.sale.SaleOrderDetailDTO;
import com.test.retail.app.scheduler.CompareBalanceSchedule;
import com.test.retail.domain.Product;
import com.test.retail.infra.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class CompareBalanceScheduleTests {

	@InjectMocks
	CompareBalanceSchedule schedule;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CustomerClientService customerClientService;

	@Mock
	private SaleProductClientService saleProductClientService;
	
	@Test
	void testCheckBalance() {
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Mockito.when(this.customerClientService.totalAmountByDate(today)).thenReturn(10d);
		
		
		int productId = 1;
		List<SaleOrderDetailDTO> dtos = new ArrayList<>();
		dtos.add(new SaleOrderDetailDTO(productId, 1));
		SaleOrderDTO dto = new SaleOrderDTO(1, dtos);
		
		Mockito.when(saleProductClientService.saleOrderLst(today)).thenReturn(Arrays.asList(dto));
		
		Set<Integer> productIds = Arrays.asList(productId).stream().collect(Collectors.toSet());

		Mockito.when(productRepository.findByIds(productIds)).thenReturn(Arrays.asList(new Product(1, "001", "Demo", 50d, 20, "Demo")));
		
		this.schedule.compareBalance();
	}
}
