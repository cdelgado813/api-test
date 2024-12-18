package com.test.priceapi.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "BRAND", catalog = "")
@Data
@NoArgsConstructor
public class BrandEntity implements Serializable{

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    public BrandEntity(Integer brandId) {
        this.brandId = brandId;
    }

    @Id
    private Integer brandId;

    private String brandName;

}
