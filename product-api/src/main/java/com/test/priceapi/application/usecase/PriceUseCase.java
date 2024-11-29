package com.test.priceapi.application.usecase;

import java.time.LocalDateTime;
import java.util.List;

import com.test.priceapi.domain.model.Price;
import com.test.priceapi.domain.port.in.RetrievePriceUseCase;
import com.test.priceapi.domain.port.out.PriceRepository;
import org.springframework.stereotype.Service;

import com.test.priceapi.domain.service.PriceManager;

@Service
public class PriceUseCase implements RetrievePriceUseCase {

    private final PriceManager priceManager;
    private final PriceRepository priceRepository;

    public PriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
        this.priceManager = new PriceManager();
    }

    @Override
    public Price getActivePrice(LocalDateTime priceStartDate, Integer productId, Integer brandId) {
        if (priceStartDate == null || productId == null || brandId == null) {
            throw new IllegalArgumentException("Los parámetros priceStartDate, productId y brandId no pueden ser nulos");
        }

        List<Price> prices = priceRepository.getPrices(priceStartDate, productId, brandId);

        if (prices.isEmpty()) {
            return null;
        }
        return priceManager.getTheActivePrice(prices);

    }

}
