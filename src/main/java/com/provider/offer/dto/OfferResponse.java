package com.provider.offer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferResponse {

    private String message;

    private OfferDetails offerDetails;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OfferDetails getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(OfferDetails offerDetails) {
        this.offerDetails = offerDetails;
    }


    public static final class OfferResponseBuilder {
        private String message;
        private OfferDetails offerDetails;

        private OfferResponseBuilder() {
        }

        public static OfferResponseBuilder anOfferResponse() {
            return new OfferResponseBuilder();
        }

        public OfferResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public OfferResponseBuilder withOfferDetails(OfferDetails offerDetails) {
            this.offerDetails = offerDetails;
            return this;
        }

        public OfferResponse build() {
            OfferResponse offerResponse = new OfferResponse();
            offerResponse.setMessage(message);
            offerResponse.setOfferDetails(offerDetails);
            return offerResponse;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferResponse response = (OfferResponse) o;
        return message.equals(response.message) &&
                offerDetails.equals(response.offerDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, offerDetails);
    }
}
