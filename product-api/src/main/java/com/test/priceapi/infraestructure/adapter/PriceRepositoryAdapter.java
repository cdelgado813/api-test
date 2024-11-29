package com.test.priceapi.infraestructure.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.test.priceapi.infraestructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Repository;

import com.test.priceapi.domain.model.Price;
import com.test.priceapi.domain.port.out.PriceRepository;
import com.test.priceapi.infraestructure.persistence.service.PricePersistenceService;

@Repository
public class PriceRepositoryAdapter implements PriceRepository{

    private final PricePersistenceService pricePersistenceService;

    public PriceRepositoryAdapter(PricePersistenceService pricePersistenceService) {
        this.pricePersistenceService = pricePersistenceService;
    }

    @Override
    public List<Price> getPrices(LocalDateTime priceStartDate, Integer productId, Integer brandId) {

        Optional<List<PriceEntity>> priceEntities = pricePersistenceService.findActivePrices(priceStartDate, productId, brandId);

        return priceEntities.map(entities -> entities.stream().map(PriceEntity::toModel).toList()).orElseGet(List::of);
    }
    
}
