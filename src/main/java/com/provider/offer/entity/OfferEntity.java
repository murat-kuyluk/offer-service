package com.provider.offer.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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


    public static final class OfferBuilder {
        private Integer id;
        private String description;
        private BigDecimal price;
        private String currency;
        private String status;

        private OfferBuilder() {
        }

        public static OfferBuilder anOffer() {
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

        public OfferEntity build() {
            OfferEntity offerEntity = new OfferEntity();
            offerEntity.setId(id);
            offerEntity.setDescription(description);
            offerEntity.setPrice(price);
            offerEntity.setCurrency(currency);
            offerEntity.setStatus(status);
            return offerEntity;
        }
    }
}
