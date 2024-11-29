package com.test.priceapi.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Price {

    private Integer productId;

    private Integer brandId;

    private Integer priceList;

    private Integer priority;

    private LocalDateTime  priceStartDate;

    private LocalDateTime priceEndDate;

    private Float activePrice;

}
