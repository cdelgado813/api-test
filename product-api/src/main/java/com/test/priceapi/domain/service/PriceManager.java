package com.test.priceapi.domain.service;

import java.util.Comparator;
import java.util.List;

import com.test.priceapi.domain.model.Price;

public class PriceManager {

    public Price getTheActivePrice(List<Price> priceList){
        return priceList.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElse(null);
    }
    
}
