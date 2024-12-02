package com.test.priceapi.domain.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.test.priceapi.domain.exception.PriceNotFoundException;
import com.test.priceapi.domain.model.Price;
import com.test.priceapi.domain.port.in.GetPriceUseCase;
import com.test.priceapi.domain.port.out.PriceRepository;

public class PriceDomainService implements GetPriceUseCase {

    private final PriceRepository priceRepository;

    public PriceDomainService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getTheActivePrice(List<Price> priceList){
        return priceList.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElse(null);
    }

    @Override
    public Price getActivePrice(LocalDateTime priceStartDate, Integer productId, Integer brandId) {
        try{
            List<Price> priceList = priceRepository.getPrices(priceStartDate, productId, brandId);
            return getTheActivePrice(priceList);
        }catch (Exception e){
            throw new PriceNotFoundException("No price was found");
        }

    }
}
