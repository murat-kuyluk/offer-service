package com.provider.offer.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferResponse;
import com.provider.offer.service.OfferService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static com.provider.offer.dto.ExpireTime.ExpireTimeBuilder.*;
import static com.provider.offer.dto.OfferDetails.OfferDetailsBuilder.*;
import static com.provider.offer.dto.OfferRequest.OfferRequestBuilder.*;
import static com.provider.offer.dto.OfferResponse.OfferResponseBuilder.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OfferControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OfferService offerService;

    @InjectMocks
    private OfferController controller;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void createOffer_shouldCreateAnOfferAndReturn201OK_whenRequestIsValid() throws Exception {

        OfferRequest offerRequest = anOfferRequest()
                .withDescription("Test offerRequest")
                .withPrice(BigDecimal.valueOf(12.99))
                .withCurrency("GBP")
                .withExpireTime(anExpireTime()
                        .withTime(1)
                        .withUnit(TimeUnit.DAYS)
                        .build())
                .build();

        String requestBody = toJson(offerRequest);

        OfferDetails offerDetails = anOfferDetails()
                .withId(1)
                .withDescription("Test offerRequest")
                .withExpireTime("1-Day")
                .withPrice("£12.99")
                .withStatus("VALID")
                .build();

        OfferResponse offerResponse = anOfferResponse()
                .withMessage("Success")
                .withOfferDetails(offerDetails)
                .build();

        String responseBody = toJson(offerResponse);

        when(offerService.createOffer(Mockito.any(OfferRequest.class))).thenReturn(offerDetails);

        mockMvc.perform(post("/offers")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(responseBody))
        ;

        verify(offerService).createOffer(offerRequest);
    }

    private <T> String toJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}