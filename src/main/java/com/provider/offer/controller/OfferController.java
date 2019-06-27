package com.provider.offer.controller;

import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferResponse;
import com.provider.offer.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.provider.offer.dto.OfferResponse.OfferResponseBuilder.*;

@RestController
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createOffer(@RequestBody OfferRequest offer){
        OfferDetails offerDetails = offerService.createOffer(offer);
        return Optional.ofNullable(offerDetails)
                .filter(od -> od.getId() != null)
                .map(this::createSuccessResponse)
                .orElse(createFailureResponse());
    }

    private ResponseEntity<OfferResponse> createSuccessResponse(OfferDetails offerDetails){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(anOfferResponse()
                        .withMessage("A new offer created successfully")
                        .withOfferDetails(offerDetails)
                        .build());
    }

    private ResponseEntity<OfferResponse> createFailureResponse(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(anOfferResponse()
                        .withMessage("Failed to create a new offer.")
                        .build());
    }

}
