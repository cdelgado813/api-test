package com.test.priceapi.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;

public class Price {

    private Integer productId;

    private Integer brandId;

    private Integer priceList;

    private Integer priority;

    private LocalDateTime  priceStartDate;

    private LocalDateTime priceEndDate;

    private Float activePrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getPriceStartDate() {
        return priceStartDate;
    }

    public void setPriceStartDate(LocalDateTime priceStartDate) {
        this.priceStartDate = priceStartDate;
    }

    public LocalDateTime getPriceEndDate() {
        return priceEndDate;
    }

    public void setPriceEndDate(LocalDateTime priceEndDate) {
        this.priceEndDate = priceEndDate;
    }

    public Float getActivePrice() {
        return activePrice;
    }

    public void setActivePrice(Float activePrice) {
        this.activePrice = activePrice;
    }

    private Price(Builder builder) {
        this.productId = builder.productId;
        this.brandId = builder.brandId;
        this.priceList = builder.priceList;
        this.priority = builder.priority;
        this.priceStartDate = builder.priceStartDate;
        this.priceEndDate = builder.priceEndDate;
        this.activePrice = builder.activePrice;
    }

    public static class Builder {
        private Integer productId;
        private Integer brandId;
        private Integer priceList;
        private Integer priority;
        private LocalDateTime priceStartDate;
        private LocalDateTime priceEndDate;
        private Float activePrice;

        public Builder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder brandId(Integer brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder priceList(Integer priceList) {
            this.priceList = priceList;
            return this;
        }

        public Builder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public Builder priceStartDate(LocalDateTime priceStartDate) {
            this.priceStartDate = priceStartDate;
            return this;
        }

        public Builder priceEndDate(LocalDateTime priceEndDate) {
            this.priceEndDate = priceEndDate;
            return this;
        }

        public Builder activePrice(Float activePrice) {
            this.activePrice = activePrice;
            return this;
        }

        public Price build() {
            return new Price(this);
        }

    }

}
