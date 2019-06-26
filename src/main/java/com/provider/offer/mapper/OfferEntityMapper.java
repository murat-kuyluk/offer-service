package com.provider.offer.mapper;

import com.provider.offer.dto.OfferRequest;
import com.provider.offer.dto.OfferResponse;
import com.provider.offer.entity.OfferEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface OfferEntityMapper {

    OfferEntity mapToOfferEntity(OfferRequest offerRequest);

    OfferResponse mapToOfferResponse(OfferEntity offerEntity);
}
