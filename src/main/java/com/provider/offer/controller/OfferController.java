package com.provider.offer.controller;

import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferResponse;
import com.provider.offer.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.provider.offer.dto.OfferResponse.OfferResponseBuilder.*;

@RestController
public class OfferController {

    private static final String OFFERS_URL = "/offers";
    private static final String OFFERS_RETRIEVE_URL = OFFERS_URL + "/{id}";
    private static final String OFFERS_CANCEL_URL = OFFERS_RETRIEVE_URL + "/cancel";

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping(value = OFFERS_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createOffer(@RequestBody OfferRequest offer){
        OfferDetails offerDetails = offerService.createOffer(offer);
        return Optional.ofNullable(offerDetails)
                .filter(od -> od.getId() != null)
                .map(this::createSuccessResponse)
                .orElse(createFailureResponse());
    }

    @GetMapping(value = OFFERS_RETRIEVE_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity retrieveOffer(@PathVariable Integer id){
        OfferDetails offerDetails = offerService.retrieveOffer(id);
        return createResponse(offerDetails);
    }

    @PutMapping(value = OFFERS_CANCEL_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity cancelOffer(@PathVariable Integer id){
        OfferDetails offerDetails = offerService.cancelOffer(id);
        return createResponse(offerDetails);
    }

    private ResponseEntity createResponse(OfferDetails offerDetails) {
        if (!Optional.ofNullable(offerDetails).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Offer not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(offerDetails);
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
