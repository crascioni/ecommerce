package com.worldpay.rascioni.ecommerce.repository;

import java.util.List;

import com.worldpay.rascioni.ecommerce.bean.Offer;


public interface OfferRepository {
    
    public List<Offer> getOffers();
    
    public void addOffer(Offer bean);
}
