package com.provider.offer.mapper;

import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferResponse;
import com.provider.offer.entity.OfferEntity;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class OfferEntityEntityMapperTest {

    private OfferEntityMapper mapper;
    @Before
    public void setUp() {
        mapper = new OfferEntityMapperImpl();
    }

    @Test
    public void shouldMapToOfferEntityFromOfferRequest() {
        OfferRequest offerRequest = OfferRequest.OfferRequestBuilder.anOfferRequest()
                .withDescription("Test offerRequest")
                .withPrice(BigDecimal.valueOf(12.99))
                .withCurrency("GBP")
                .build();

        OfferEntity actual = mapper.mapToOfferEntity(offerRequest);

        assertThat(actual).isNotNull();
        assertThat(actual.getCurrency()).isEqualTo("GBP");
        assertThat(actual.getPrice()).isEqualTo(BigDecimal.valueOf(12.99));
        assertThat(actual.getDescription()).isEqualTo("Test offerRequest");
    }

    @Test
    public void shouldReturnNull_whenTheRequestIsNull() {

        OfferEntity actual = mapper.mapToOfferEntity(null);

        assertThat(actual).isNull();
    }

    @Test
    public void shouldMapToOfferResponseFromOfferEntity() {

        OfferEntity offerEntity = OfferEntity.OfferBuilder.anOffer()
                .withId(1)
                .withCurrency("GBP")
                .withDescription("Test")
                .withPrice(BigDecimal.valueOf(12.99))
                .withStatus("VALID")
                .build();

        OfferResponse actual = mapper.mapToOfferResponse(offerEntity);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getStatus()).isEqualTo("VALID");
        assertThat(actual.getMessage()).isBlank();
    }

    @Test
    public void shouldReturnNull_whenOfferEntityIsNull() {

        OfferResponse actual = mapper.mapToOfferResponse(null);

        assertThat(actual).isNull();
    }
}