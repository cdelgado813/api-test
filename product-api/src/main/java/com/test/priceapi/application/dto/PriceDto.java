package com.test.priceapi.application.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.priceapi.domain.model.Price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDto {

    @JsonProperty("productId")
    Integer productId;

    @JsonProperty("brandId")
    Integer brandId;

    @JsonProperty("priceList")
    Integer priceList;

    @JsonProperty("priceStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime priceStartDate;

    @JsonProperty("priceEndDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime priceEndDate;

    @JsonProperty("price")
    Float price;

    public PriceDto(Price price) {

        Optional.ofNullable(price).ifPresent(p -> {
            this.productId = p.getProductId();
            this.brandId = p.getBrandId();
            this.priceList = p.getPriceList();
            this.price = p.getActivePrice();
            this.priceStartDate = p.getPriceStartDate();
            this.priceEndDate = p.getPriceEndDate();
        });

    }

    public Price toModel() {

        return Price.builder()
                .productId(productId)
                .brandId(brandId)
                .activePrice(price)
                .priceList(priceList)
                .priceStartDate(priceStartDate)
                .priceEndDate(priceEndDate)
                .build();

    }

}
