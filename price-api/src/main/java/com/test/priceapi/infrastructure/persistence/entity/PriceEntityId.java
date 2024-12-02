package com.test.priceapi.infrastructure.persistence.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntityId {

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brandEntity;

    //PRICE_LIST: Identificador de la tarifa de precios aplicable.
    @Column(name = "price_list", nullable = false)
    private Integer priceListId;

    //PRODUCT_ID: Identificador código de producto.
    @Column(name = "product_id", nullable = false)
    private Integer productId;

}
