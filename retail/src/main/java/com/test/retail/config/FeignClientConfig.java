package com.test.retail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.retail.app.external.CustomerClientService;
import com.test.retail.app.external.SaleProductClientService;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Configuration
public class FeignClientConfig {
	@Value("${client.customer-service}")
    private String customerServiceURL;
	
	@Value("${client.sale-service}")
    private String saleServiceURL;

	@Bean
    public CustomerClientService customerClient() {
        return Feign.builder()
	    		  .client(new OkHttpClient())
	    		  .encoder(new GsonEncoder())
	    		  .decoder(new GsonDecoder())
	    		  .logger(new Slf4jLogger(CustomerClientService.class))
	    		  .logLevel(Logger.Level.FULL)
	    		  .target(CustomerClientService.class, this.customerServiceURL + "/customer");
    }
	
	@Bean
    public SaleProductClientService productClient() {
        return Feign.builder()
	    		  .client(new OkHttpClient())
	    		  .encoder(new GsonEncoder())
	    		  .decoder(new GsonDecoder())
	    		  .logger(new Slf4jLogger(SaleProductClientService.class))
	    		  .logLevel(Logger.Level.FULL)
	    		  .target(SaleProductClientService.class, this.saleServiceURL + "/sale-order");
    }
}
