package com.provider.offer.service;

import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferResponse;

public interface OfferService {

     OfferResponse createOffer(OfferRequest offer);
}
