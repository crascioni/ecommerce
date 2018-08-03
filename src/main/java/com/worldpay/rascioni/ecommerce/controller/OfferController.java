package com.worldpay.rascioni.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.query.OfferDTO;
import com.worldpay.rascioni.ecommerce.service.OfferService;

/**
 * 
 * @author Christian
 * Some extra information about the application:
 * I've used some design patters (MVC, Singleton, CQRS) and some libraries to build the application.
 * About testing, I've used JUnit and written them using TDD  
 *
 */
@RestController
public class OfferController {
    
    @Autowired
    private OfferService offerService;
    
    /**
     * Get all the offers
     * @return a set of Offers
     */
    @RequestMapping(value = "/getOffers", method = RequestMethod.GET)
    public ResponseEntity<List<OfferDTO>> getOffers() {
        return new ResponseEntity<List<OfferDTO>>(offerService.getOffers(), HttpStatus.OK);
    }
    
    /**
     * Add a new offer
     * @param bean the new offer
     * @return the result of the operation
     */
    @RequestMapping(value = "/addOffer", method = RequestMethod.POST)
    public ResponseEntity<String> addOffer(@RequestBody AddOfferCommand bean) {
        return offerService.addOffer(bean);
    }
    
    /**
     * Remove an offer
     * @param bean the offer which has to be removed
     * @return the result of the operation
     */
    @RequestMapping(value = "/removeOffer", method = RequestMethod.POST)
    public ResponseEntity<String> removeOffer(@RequestBody RemoveOfferCommand bean) {
        return offerService.removeOffer(bean);
    }

}
