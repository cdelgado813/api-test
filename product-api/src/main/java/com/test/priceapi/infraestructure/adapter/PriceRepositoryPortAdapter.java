package com.test.priceapi.infraestructure.adapter;

import java.time.LocalDateTime;
import java.util.List;

import com.test.priceapi.infraestructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Repository;

import com.test.priceapi.domain.model.Price;
import com.test.priceapi.domain.port.out.PriceRepository;
import com.test.priceapi.infraestructure.persistence.service.PricePersistenceService;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PriceRepositoryPortAdapter implements PriceRepository{

    private final PricePersistenceService pricePersistenceService;

    @Override
    public List<Price> getPrices(LocalDateTime priceStartDate, Integer productId, Integer brandId) {

        List<PriceEntity> priceEntities = pricePersistenceService.findActivePrices(priceStartDate, productId, brandId);

        return priceEntities != null ?
                priceEntities.stream().map(PriceEntity::toModel).toList() :
                List.of();
    }
    
}
