package com.test.retail.app.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CompareBalanceSchedule {

	@Scheduled(cron = "0 0 0 * * *")
	public void compareBalance() {
		// TODO
		System.out.println(LocalDateTime.now().toString());
	}
}
