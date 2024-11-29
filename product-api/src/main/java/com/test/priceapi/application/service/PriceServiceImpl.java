package com.test.priceapi.application.service;

import com.test.priceapi.application.dto.PriceDto;


import com.test.priceapi.application.ports.PriceService;
import com.test.priceapi.domain.port.in.RetrievePriceUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final RetrievePriceUseCase retrievePriceUseCase;

    public PriceDto findActivePrice(LocalDateTime priceStartDate, Integer productId, Integer brandId) {
        return new PriceDto(retrievePriceUseCase.getActivePrice(priceStartDate, productId, brandId));

    }
}
