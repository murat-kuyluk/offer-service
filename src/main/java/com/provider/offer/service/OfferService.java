package com.provider.offer.service;

import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;

public interface OfferService {

     OfferDetails createOffer(OfferRequest offer);

     OfferDetails retrieveOffer(Integer id);
}
