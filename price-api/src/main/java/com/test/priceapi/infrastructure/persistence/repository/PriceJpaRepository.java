package com.test.priceapi.infrastructure.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.test.priceapi.infrastructure.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.priceapi.infrastructure.persistence.entity.PriceEntity;
import com.test.priceapi.infrastructure.persistence.entity.PriceEntityId;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, PriceEntityId> {


    /**
     * Se podría utilizar esta query para simplificar mucho más el proyecto
     * @Query("SELECT p FROM PriceEntity p WHERE :priceStartDate BETWEEN p.priceStartDate AND p.priceEndDate AND p.priceEntityId.productId = :productId AND p.priceEntityId.brandEntity = :brandId ORDER BY p.priority DESC")
     **/
    @Query("SELECT p FROM PriceEntity p WHERE :priceStartDate BETWEEN p.priceStartDate AND p.priceEndDate AND p.priceEntityId.productId = :productId AND p.priceEntityId.brandEntity = :brandId")
    Optional<List<PriceEntity>> findActivePrices(Date priceStartDate, Integer productId, BrandEntity brandId);
}
