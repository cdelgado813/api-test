package com.test.priceapi.infrastructure.persistence.entity;

import java.sql.Timestamp;

import com.test.priceapi.domain.model.Price;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRICES", catalog = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {

    /*
     * Añado una clase que implementa el ID único de la tabla PRICE a pesar de no estar definido en el manual
     */
    @EmbeddedId
    private PriceEntityId priceEntityId;

    //START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
    @Column(name = "start_date", nullable = false)
    private Timestamp priceStartDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp priceEndDate;

    //PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
    @Column(name = "priority", nullable = false)
    private Integer priority;

    //PRICE: precio final de venta.
    @Column(name = "price", nullable = false)
    private Float price;

    //CURR: iso de la moneda.
    @Column(name = "curr", nullable = false)
    private String currency;

    public Price toModel() {

        return new Price.Builder()
                .productId(priceEntityId.getProductId())
                .brandId(priceEntityId.getBrandEntity().getBrandId())
                .priceList(priceEntityId.getPriceListId())
                .priceStartDate(priceStartDate.toLocalDateTime())
                .activePrice(price)
                .priority(priority)
                .priceEndDate(priceEndDate.toLocalDateTime())
                .build();

    }

}
