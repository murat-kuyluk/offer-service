package com.provider.offer.service;

import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.entity.OfferEntity;
import com.provider.offer.mapper.OfferEntityMapper;
import com.provider.offer.mapper.OfferEntityMapperImpl;
import com.provider.offer.repository.OfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.provider.offer.dto.ExpireTime.ExpireTimeBuilder.anExpireTime;
import static com.provider.offer.dto.OfferRequest.OfferRequestBuilder.*;
import static com.provider.offer.entity.OfferEntity.OfferBuilder.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

    private OfferService offerService;

    @Mock
    private OfferRepository repository;

    private OfferEntityMapper mapper;

    @Before
    public void setUp() {
        mapper = new OfferEntityMapperImpl();
        offerService = new OfferServiceImpl(repository, mapper);
    }

    @Test
    public void createOffer_shouldCreateAnOffer() {

        OfferEntity savedOffer = createEntity(1, "2-DAYS", "VALID");
        when(repository.save(any(OfferEntity.class))).thenReturn(savedOffer);

        OfferRequest offerRequest = anOfferRequest()
                .withDescription("Test offer")
                .withPrice(BigDecimal.valueOf(12.99))
                .withCurrency("GBP")
                .withExpireTime(anExpireTime()
                        .withTime(2)
                        .withUnit(TimeUnit.DAYS)
                        .build())
                .build();
        OfferDetails offerDetails = offerService.createOffer(offerRequest);

        assertThat(offerDetails).isNotNull();
        OfferEntity unSavedOffer = createEntity(null, "2-DAYS", "VALID");
        verify(repository).save(unSavedOffer);
    }

    @Test
    public void shouldExpireOffer_afterPeriodOfTime() throws InterruptedException {

        OfferEntity savedOffer = createEntity(1, "1000-MILLISECONDS", "VALID");
        when(repository.save(any(OfferEntity.class))).thenReturn(savedOffer);

        OfferRequest offerRequest = anOfferRequest()
                .withDescription("Test offer")
                .withPrice(BigDecimal.valueOf(12.99))
                .withCurrency("GBP")
                .withExpireTime(anExpireTime()
                        .withTime(1000)
                        .withUnit(TimeUnit.MILLISECONDS)
                        .build())
                .build();
        offerService.createOffer(offerRequest);

        when(repository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(savedOffer));

        TimeUnit.MILLISECONDS.sleep(1010);

        OfferEntity updatedOffer = createEntity(1, "1000-MILLISECONDS", "EXPIRED");
        verify(repository).save(updatedOffer);
    }

    private OfferEntity createEntity(Integer id, String expireTime, String status) {
        return anOfferEntity()
                .withId(id)
                .withStatus(status)
                .withCurrency("GBP")
                .withDescription("Test offer")
                .withPrice(BigDecimal.valueOf(12.99))
                .withExpireTime(expireTime)
                .build();
    }
}