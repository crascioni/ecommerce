package com.worldpay.rascioni.ecommerce.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;
import com.worldpay.rascioni.ecommerce.service.OfferService;
import com.worldpay.rascioni.ecommerce.utility.MockStorage;

@Service
public class OfferServiceImpl implements OfferService {
    
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Set<Offer> getOffers() {
        return offerRepository.getOffers();
    }

    @Override
    public ResponseEntity<String> addOffer(AddOfferCommand bean) {
        if(isPresent(bean.getTitle()) != null) {
            return new ResponseEntity<String>("The element has already been added", HttpStatus.CONFLICT);
        }       
        Offer offer = new  Offer(
                bean.getTitle(), 
                bean.getDesc(), 
                bean.getPrice(), 
                bean.getExpTime()
                );
        
        offerRepository.addOffer(offer);
        return new ResponseEntity<String>("The element has been added to the collection", HttpStatus.OK);
    }
    
    /**
     * Check if an offer with this name is already inside the collection
     * @param title the name of the new offer
     * @return if present, return the offer
     */
    private Offer isPresent(String title) {
        for(Offer offer: MockStorage.getInstance().getData()) {
            if(offer.getTitle().equals(title)) { 
                return offer;
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeOffer(RemoveOfferCommand bean) {
        Offer offer = isPresent(bean.getTitle());
        if(offer == null) {
            return new ResponseEntity<String>("The element doesn't exist", HttpStatus.BAD_REQUEST);
        }       
        offerRepository.removeOffer(offer);
        return new ResponseEntity<String>("The element has been removed", HttpStatus.OK);
    }

}
