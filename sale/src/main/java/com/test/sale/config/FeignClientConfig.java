package com.test.sale.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.sale.app.external.CustomerClientService;
import com.test.sale.app.external.ProductClientService;

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
	
	@Value("${client.product-service}")
    private String productServiceURL;

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
    public ProductClientService productClient() {
        return Feign.builder()
	    		  .client(new OkHttpClient())
	    		  .encoder(new GsonEncoder())
	    		  .decoder(new GsonDecoder())
	    		  .logger(new Slf4jLogger(ProductClientService.class))
	    		  .logLevel(Logger.Level.FULL)
	    		  .target(ProductClientService.class, this.productServiceURL + "/product");
    }

}
