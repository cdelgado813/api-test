package com.test.priceapi.infrastructure.persistence.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.test.priceapi.infrastructure.exception.InfrastructureException;
import com.test.priceapi.infrastructure.persistence.entity.BrandEntity;
import org.springframework.stereotype.Service;

import com.test.priceapi.infrastructure.persistence.entity.PriceEntity;
import com.test.priceapi.infrastructure.persistence.repository.PriceJpaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PricePersistenceService {

    PriceJpaRepository priceJpaRepository;

    /**
     * Recupera una lista de precios activos para un producto y marca específicos en una fecha dada.
     *
     * @param priceStartDate La fecha para la cual se buscan los precios activos.
     * @param productId      El identificador del producto.
     * @param brandId        El identificador de la marca.
     * @return Una lista de precios activos o una lista vacía sí ocurre un error.
     */
    public Optional<List<PriceEntity>> findActivePrices(LocalDateTime priceStartDate, Integer productId, Integer brandId) {
        try {
            return priceJpaRepository.findActivePrices(
                    Timestamp.valueOf(priceStartDate), productId, new BrandEntity(brandId));
        } catch (Exception ex) {
            throw new InfrastructureException("Error accessing database");
        }


    }


}
