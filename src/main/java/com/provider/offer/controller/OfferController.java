package com.provider.offer.controller;

import com.provider.offer.dto.OfferRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    @PostMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createOffer(@RequestBody OfferRequest offer){
        return ResponseEntity.status(HttpStatus.CREATED).body("Offer created");
    }

}
