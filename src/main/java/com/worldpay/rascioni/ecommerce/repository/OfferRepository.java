package com.worldpay.rascioni.ecommerce.repository;

import java.util.Set;

import com.worldpay.rascioni.ecommerce.bean.Offer;


public interface OfferRepository {
    
    public Set<Offer> getOffers();
    
    public void addOffer(Offer bean);

    public void removeOffer(Offer offer);
    
    public Offer findByTitle(String title);

    public Float getHighestPrice();
}
