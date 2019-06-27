package com.provider.offer.dto;

public class OfferDetails {

    private Integer id;

    private String description;

    private String price;

    private OfferStatus status;

    private String expireTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }


    public static final class OfferDetailsBuilder {
        private Integer id;
        private String description;
        private String price;
        private OfferStatus status;
        private String expireTime;

        private OfferDetailsBuilder() {
        }

        public static OfferDetailsBuilder anOfferDetails() {
            return new OfferDetailsBuilder();
        }

        public OfferDetailsBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OfferDetailsBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OfferDetailsBuilder withPrice(String price) {
            this.price = price;
            return this;
        }

        public OfferDetailsBuilder withStatus(OfferStatus status) {
            this.status = status;
            return this;
        }

        public OfferDetailsBuilder withExpireTime(String expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public OfferDetails build() {
            OfferDetails offerDetails = new OfferDetails();
            offerDetails.setId(id);
            offerDetails.setDescription(description);
            offerDetails.setPrice(price);
            offerDetails.setStatus(status);
            offerDetails.setExpireTime(expireTime);
            return offerDetails;
        }
    }
}
