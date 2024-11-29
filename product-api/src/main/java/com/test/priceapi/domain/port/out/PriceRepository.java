package com.test.priceapi.domain.port.out;

import com.test.priceapi.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> getPrices(LocalDateTime priceStartDate, Integer productId, Integer brandId);
}
