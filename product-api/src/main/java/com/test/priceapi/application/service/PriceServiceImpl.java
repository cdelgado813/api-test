package com.test.priceapi.application.service;

import com.test.priceapi.application.dto.PriceDto;


import com.test.priceapi.application.port.PriceService;
import com.test.priceapi.domain.port.in.GetPriceUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final GetPriceUseCase getPriceUseCase;

    public PriceDto findActivePrice(LocalDateTime priceStartDate, Integer productId, Integer brandId) {
        return new PriceDto(getPriceUseCase.getActivePrice(priceStartDate, productId, brandId));
    }
}
