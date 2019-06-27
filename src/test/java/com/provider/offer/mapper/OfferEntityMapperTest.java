package com.provider.offer.mapper;

import com.provider.offer.dto.ExpireTime;
import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferStatus;
import com.provider.offer.entity.OfferEntity;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class OfferEntityMapperTest {

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
                .withExpireTime(ExpireTime.ExpireTimeBuilder.anExpireTime()
                        .withUnit(TimeUnit.DAYS)
                        .withTime(2)
                        .build())
                .build();

        OfferEntity actual = mapper.mapToOfferEntity(offerRequest);

        assertThat(actual).isNotNull();
        assertThat(actual.getCurrency()).isEqualTo("GBP");
        assertThat(actual.getPrice()).isEqualTo(BigDecimal.valueOf(12.99));
        assertThat(actual.getDescription()).isEqualTo("Test offerRequest");
        assertThat(actual.getExpireTime()).isEqualTo("2-DAYS");
        assertThat(actual.getStatus()).isEqualTo("VALID");
    }

    @Test
    public void shouldReturnNull_whenTheRequestIsNull() {

        OfferEntity actual = mapper.mapToOfferEntity(null);

        assertThat(actual).isNull();
    }

    @Test
    public void shouldMapToOfferDetailsFromOfferEntity() {

        OfferEntity offerEntity = OfferEntity.OfferBuilder.anOfferEntity()
                .withId(1)
                .withCurrency("GBP")
                .withDescription("Test")
                .withPrice(BigDecimal.valueOf(12.99))
                .withExpireTime("2-DAYS")
                .withStatus("VALID")
                .build();

        OfferDetails actual = mapper.mapToOfferDetails(offerEntity);

        assertThat(actual).isNotNull();

        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getDescription()).isEqualTo("Test");
        assertThat(actual.getPrice()).isEqualTo("£12.99");
        assertThat(actual.getExpireTime()).isEqualTo("2-DAYS");
        assertThat(actual.getStatus()).isEqualTo(OfferStatus.VALID);
    }

    @Test
    public void shouldReturnNull_whenOfferEntityIsNull() {

        OfferDetails actual = mapper.mapToOfferDetails(null);

        assertThat(actual).isNull();
    }
}