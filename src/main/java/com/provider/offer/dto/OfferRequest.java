package com.provider.offer.dto;

import java.math.BigDecimal;

public class OfferRequest {

    private String description;

    private BigDecimal price;

    private String currency;

    private ExpireTime expireTime;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ExpireTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(ExpireTime expireTime) {
        this.expireTime = expireTime;
    }


    public static final class OfferRequestBuilder {
        private String description;
        private BigDecimal price;
        private String currency;
        private ExpireTime expireTime;

        private OfferRequestBuilder() {
        }

        public static OfferRequestBuilder anOfferRequest() {
            return new OfferRequestBuilder();
        }

        public OfferRequestBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OfferRequestBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OfferRequestBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public OfferRequestBuilder withExpireTime(ExpireTime expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public OfferRequest build() {
            OfferRequest offerRequest = new OfferRequest();
            offerRequest.setDescription(description);
            offerRequest.setPrice(price);
            offerRequest.setCurrency(currency);
            offerRequest.setExpireTime(expireTime);
            return offerRequest;
        }
    }
}
