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
import com.worldpay.rascioni.ecommerce.exception.InternalServerErrorException;
import com.worldpay.rascioni.ecommerce.exception.MissingDataException;
import com.worldpay.rascioni.ecommerce.exception.OfferAlreadyAddedException;
import com.worldpay.rascioni.ecommerce.exception.OfferNotAddedException;
import com.worldpay.rascioni.ecommerce.query.OfferDTO;
import com.worldpay.rascioni.ecommerce.service.OfferService;

/**
 * 
 * @author Christian
 * Some extra information about the application:
 * I've used some design patters and some libraries to build and test the application.
 * About testing, I've used JUnit/Mockito framework and written them using TDD/BDD.  
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
     * Add a new offer. Every exception is handled with a different HTTP status.
     * @param bean the new offer
     * @return the result of the operation
     */
    @RequestMapping(value = "/addOffer", method = RequestMethod.POST)
    public ResponseEntity<String> addOffer(@RequestBody AddOfferCommand bean) {
        ResponseEntity<String> response = null;
        try {
            response = new ResponseEntity<String>(offerService.addOffer(bean), HttpStatus.OK);
        } catch (MissingDataException e) {
            System.out.println(e);
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (OfferAlreadyAddedException e) {
            System.out.println(e);
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
            System.out.println(e);
        } catch (InternalServerErrorException e) {
            System.out.println(e);
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }      
        return response;
    }
    
    /**
     * Remove an offer. Every exception is handled with a different HTTP status.
     * @param bean the offer which has to be removed
     * @return the result of the operation
     */
    @RequestMapping(value = "/removeOffer", method = RequestMethod.POST)
    public ResponseEntity<String> removeOffer(@RequestBody RemoveOfferCommand bean) {
        ResponseEntity<String> response = null;
        try {
            response = new ResponseEntity<String>( offerService.removeOffer(bean), HttpStatus.OK);
        } catch (MissingDataException e) {
            System.out.println(e);
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (OfferNotAddedException e) {
            System.out.println(e);
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InternalServerErrorException e) {
            System.out.println(e);
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
