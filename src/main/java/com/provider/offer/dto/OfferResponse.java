package com.provider.offer.dto;

import java.util.Objects;

public class OfferResponse {

    private Integer id;

    private String status;

    private OfferRequest offerDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OfferRequest getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(OfferRequest offerDetails) {
        this.offerDetails = offerDetails;
    }


    public static final class OfferResponseBuilder {
        private Integer id;
        private String status;
        private OfferRequest offerDetails;

        private OfferResponseBuilder() {
        }

        public static OfferResponseBuilder anOfferResponse() {
            return new OfferResponseBuilder();
        }

        public OfferResponseBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OfferResponseBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public OfferResponseBuilder withOfferDetails(OfferRequest offerDetails) {
            this.offerDetails = offerDetails;
            return this;
        }

        public OfferResponse build() {
            OfferResponse offerResponse = new OfferResponse();
            offerResponse.setId(id);
            offerResponse.setStatus(status);
            offerResponse.setOfferDetails(offerDetails);
            return offerResponse;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferResponse that = (OfferResponse) o;
        return id.equals(that.id) &&
                status.equals(that.status) &&
                offerDetails.equals(that.offerDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, offerDetails);
    }
}
