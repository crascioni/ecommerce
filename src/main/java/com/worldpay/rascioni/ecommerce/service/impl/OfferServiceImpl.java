package com.worldpay.rascioni.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.worldpay.rascioni.ecommerce.Constants;
import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.query.OfferDTO;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;
import com.worldpay.rascioni.ecommerce.service.OfferService;
import com.worldpay.rascioni.ecommerce.utility.Utility;

@Service
public class OfferServiceImpl implements OfferService {
    
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<OfferDTO> getOffers() {
        Set<Offer> offers = offerRepository.getOffers();
        List<OfferDTO> result = new ArrayList<>();
        for (Offer offer : offers) {
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setTitle(offer.getTitle());
            offerDTO.setDesc(offer.getDesc());
            offerDTO.setExpTime(offer.getExpTime() + " min");
            offerDTO.setPrice(offer.getPrice() + " " + Constants.CURRENCY);
            result.add(offerDTO);
        }
        return result;
    }

    @Override
    public ResponseEntity<String> addOffer(AddOfferCommand bean) {
        //Check if the input is null
        if(Utility.isNullOrEmpty(bean.getTitle()) || bean.getPrice() == 0 || bean.getExpTime() == 0) {
            return new ResponseEntity<String>(Constants.MISSING_DATA_ERROR, HttpStatus.BAD_REQUEST);
        }
        //Check if the bean has already been added
        if(Utility.isPresent(bean.getTitle()) != null) {
            return new ResponseEntity<String>(Constants.ALREADY_ADDED_ERROR, HttpStatus.CONFLICT);
        }       
        Offer offer = new  Offer(
                bean.getTitle(), 
                bean.getDesc(), 
                bean.getPrice(), 
                bean.getExpTime()
                );
        
        offerRepository.addOffer(offer);
        return new ResponseEntity<String>(Constants.INSERT_OK, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> removeOffer(RemoveOfferCommand bean) {
        //Check if the input is null
        if(Utility.isNullOrEmpty(bean.getTitle())) {
            return new ResponseEntity<String>(Constants.MISSING_DATA_ERROR, HttpStatus.BAD_REQUEST);
        }
        //Check if the bean is in the collection
        Offer offer = Utility.isPresent(bean.getTitle());
        if(offer == null) {
            return new ResponseEntity<String>(Constants.MISSING_ELEMENT_ERROR, HttpStatus.BAD_REQUEST);
        }       
        offerRepository.removeOffer(offer);
        return new ResponseEntity<String>(Constants.DELETE_OK, HttpStatus.OK);
    }

}
