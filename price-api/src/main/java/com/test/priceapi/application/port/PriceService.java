package com.test.priceapi.application.port;

import com.test.priceapi.application.dto.PriceDto;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDto findActivePrice(LocalDateTime priceStartDate, Integer productId, Integer brandId);
}
