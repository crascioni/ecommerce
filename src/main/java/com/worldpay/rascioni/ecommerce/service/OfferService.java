package com.worldpay.rascioni.ecommerce.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;

public interface OfferService {

    public Set<Offer> getOffers();
    
    public ResponseEntity<String> addOffer(AddOfferCommand bean);

    public ResponseEntity<String> removeOffer(RemoveOfferCommand bean);
}
