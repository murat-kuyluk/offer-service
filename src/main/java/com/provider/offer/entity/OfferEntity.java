package com.provider.offer.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "offers")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status")
    private String status;

    @Column(name = "expire_time")
    private String expireTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public static final class OfferBuilder {
        private Integer id;
        private String description;
        private BigDecimal price;
        private String currency;
        private String status;
        private String expireTime;

        private OfferBuilder() {
        }

        public static OfferBuilder anOfferEntity() {
            return new OfferBuilder();
        }

        public OfferBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OfferBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OfferBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OfferBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public OfferBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public OfferBuilder withExpireTime(String expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public OfferEntity build() {
            OfferEntity offerEntity = new OfferEntity();
            offerEntity.setId(id);
            offerEntity.setDescription(description);
            offerEntity.setPrice(price);
            offerEntity.setCurrency(currency);
            offerEntity.setStatus(status);
            offerEntity.setExpireTime(expireTime);
            return offerEntity;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferEntity entity = (OfferEntity) o;
        return Objects.equals(id, entity.id) &&
                Objects.equals(description, entity.description) &&
                Objects.equals(price, entity.price) &&
                Objects.equals(currency, entity.currency) &&
                Objects.equals(status, entity.status) &&
                Objects.equals(expireTime, entity.expireTime) &&
                Objects.equals(createDate, entity.createDate) &&
                Objects.equals(modifyDate, entity.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, currency, status, expireTime, createDate, modifyDate);
    }
}
