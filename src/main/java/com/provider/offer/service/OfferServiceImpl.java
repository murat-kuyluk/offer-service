package com.provider.offer.service;

import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.entity.OfferEntity;
import com.provider.offer.mapper.OfferEntityMapper;
import com.provider.offer.repository.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository repository;

    private final OfferEntityMapper mapper;

    public OfferServiceImpl(OfferRepository repository, OfferEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OfferDetails createOffer(OfferRequest offer) {

        OfferEntity savedOffer = repository.save(mapper.mapToOfferEntity(offer));
        return mapper.mapToOfferDetails(savedOffer);
    }
}
