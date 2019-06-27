package com.provider.offer.mapper;

import com.provider.offer.dto.ExpireTime;
import com.provider.offer.dto.OfferDetails;
import com.provider.offer.dto.OfferRequest;
import com.provider.offer.entity.OfferEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public abstract class OfferEntityMapper {

    @Mapping(target = "expireTime", expression = "java(getExpireTimeAsString(offerRequest.getExpireTime()))")
    public abstract OfferEntity mapToOfferEntity(OfferRequest offerRequest);

    @Mapping(target = "price", expression = "java(getPriceWithCurrency(offerEntity))")
    public abstract OfferDetails mapToOfferDetails(OfferEntity offerEntity);

    protected String getExpireTimeAsString(ExpireTime expireTime){
        return expireTime.getTime() + "-" + expireTime.getUnit().name();
    }

    protected String getPriceWithCurrency(OfferEntity offerEntity){
        BigDecimal scaledPrice = offerEntity.getPrice().setScale(2, RoundingMode.HALF_EVEN);
        return Currency.getInstance(offerEntity.getCurrency()).getSymbol().concat(scaledPrice.toString());
    }
}
