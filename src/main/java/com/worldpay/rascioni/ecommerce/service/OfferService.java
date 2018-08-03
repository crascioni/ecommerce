package com.worldpay.rascioni.ecommerce.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.query.OfferDTO;

public interface OfferService {

    public List<OfferDTO> getOffers();
    
    public ResponseEntity<String> addOffer(AddOfferCommand bean);

    public ResponseEntity<String> removeOffer(RemoveOfferCommand bean);
}
