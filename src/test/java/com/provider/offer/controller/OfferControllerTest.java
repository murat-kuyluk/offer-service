package com.provider.offer.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferResponse;
import com.provider.offer.dto.OfferStatus;
import com.provider.offer.service.OfferService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        OfferRequest offerRequest = createOfferRequest();

        String requestBody = toJson(offerRequest);

        OfferDetails offerDetails = createOfferDetails();

        OfferResponse offerResponse = anOfferResponse()
                .withMessage("A new offer created successfully")
                .withOfferDetails(offerDetails)
                .build();

        String responseBody = toJson(offerResponse);

        when(offerService.createOffer(any(OfferRequest.class))).thenReturn(offerDetails);

        mockMvc.perform(post("/offers")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(responseBody))
        ;

        verify(offerService).createOffer(offerRequest);
    }

    @Test
    public void createOffer_shouldReturnErrorMessage_whenExceptionOccurs() throws Exception {

        OfferRequest offerRequest = createOfferRequest();

        String requestBody = toJson(offerRequest);

        when(offerService.createOffer(any(OfferRequest.class))).thenReturn(anOfferDetails().build());

        OfferResponse offerResponse = anOfferResponse()
                .withMessage("Failed to create a new offer.")
                .build();

        String responseBody = toJson(offerResponse);

        mockMvc.perform(post("/offers")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(responseBody))
        ;
    }

    @Test
    public void retrieveOffer_shouldReturnOfferDetails_whenIdIsValid() throws Exception {

        OfferDetails offerDetails = createOfferDetails();
        when(offerService.retrieveOffer(any(Integer.class))).thenReturn(offerDetails);
        String expectedJson = toJson(offerDetails);

        mockMvc.perform(get("/offers/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(expectedJson))
        ;
    }

    @Test
    public void retrieveOffer_shouldReturnNotFound_whenOfferNotExist() throws Exception {

        when(offerService.retrieveOffer(any(Integer.class))).thenReturn(null);

        mockMvc.perform(get("/offers/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("Offer not found."))
        ;
    }

    @Test
    public void cancelOffer_shouldUpdateStatusOfOffer() throws Exception {

        OfferDetails offerDetails = createOfferDetails();
        offerDetails.setStatus(OfferStatus.CANCELLED);
        when(offerService.cancelOffer(any(Integer.class))).thenReturn(offerDetails);


        mockMvc.perform(put("/offers/1/cancel"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(toJson(offerDetails)))
        ;
    }

    private OfferDetails createOfferDetails() {
        return anOfferDetails()
                .withId(1)
                .withDescription("Test offerRequest")
                .withExpireTime("2-Days")
                .withPrice("£12.99")
                .withStatus(OfferStatus.VALID)
                .build();
    }

    private OfferRequest createOfferRequest() {
        return anOfferRequest()
                .withDescription("Test offerRequest")
                .withPrice(BigDecimal.valueOf(12.99))
                .withCurrency("GBP")
                .withExpireTime(anExpireTime()
                        .withTime(2)
                        .withUnit(TimeUnit.DAYS)
                        .build())
                .build();
    }

    private <T> String toJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}