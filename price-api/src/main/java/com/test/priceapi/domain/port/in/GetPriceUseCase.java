package com.test.priceapi.domain.port.in;

import com.test.priceapi.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {

    Price getActivePrice(LocalDateTime priceStartDate, Integer productId, Integer brandId);
}
