package com.worldpay.rascioni.ecommerce.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.service.OfferService;


@RestController
public class OfferController {
    
    @Autowired
    private OfferService offerService;
    
    @RequestMapping(value = "/getOffersByStatus", method = RequestMethod.GET)
    public ResponseEntity<Set<Offer>> getOffersByStatus() {
        return new ResponseEntity<Set<Offer>>(offerService.getOffers(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addOffer", method = RequestMethod.POST)
    public ResponseEntity<String> addOffer(@RequestBody AddOfferCommand bean) {
        return offerService.addOffer(bean);
    }
    
    @RequestMapping(value = "/removeOffer", method = RequestMethod.POST)
    public ResponseEntity<String> removeOffer(@RequestBody RemoveOfferCommand bean) {
        return offerService.removeOffer(bean);
    }

}
