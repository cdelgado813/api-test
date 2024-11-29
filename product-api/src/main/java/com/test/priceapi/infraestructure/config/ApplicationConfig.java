package com.test.priceapi.infraestructure.config;

import com.test.priceapi.domain.port.out.PriceRepository;
import com.test.priceapi.domain.service.PriceDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PriceDomainService priceDomainService(PriceRepository priceRepository) {
        return new PriceDomainService(priceRepository);
    }

}



