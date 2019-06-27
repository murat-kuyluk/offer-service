package com.provider.offer.service;

import com.provider.offer.dto.ExpireTime;
import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.entity.OfferEntity;
import com.provider.offer.mapper.OfferEntityMapper;
import com.provider.offer.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.Executors;

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
        scheduleOfferExpireTask(savedOffer.getId(), offer.getExpireTime());
        return mapper.mapToOfferDetails(savedOffer);
    }

    private void scheduleOfferExpireTask(Integer offerId, ExpireTime expireTime){
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            Optional<OfferEntity> offerEntity = repository.findById(offerId);
            offerEntity.map(oe -> {
                oe.setStatus("EXPIRED");
                return oe;
            }).ifPresent(oe -> repository.save(oe));
        }, expireTime.getTime(), expireTime.getUnit());
    }

}
