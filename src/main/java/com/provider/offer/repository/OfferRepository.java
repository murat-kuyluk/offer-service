package com.provider.offer.repository;

import com.provider.offer.entity.OfferEntity;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<OfferEntity, Integer> {
}
