package com.worldpay.rascioni.ecommerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;

public interface OfferService {

    public List<Offer> getOffers();
    
    public ResponseEntity<String> addOffer(AddOfferCommand bean);
}
