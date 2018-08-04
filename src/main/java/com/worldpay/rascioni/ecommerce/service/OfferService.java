package com.worldpay.rascioni.ecommerce.service;

import java.util.List;

import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.exception.InternalServerErrorException;
import com.worldpay.rascioni.ecommerce.exception.MissingDataException;
import com.worldpay.rascioni.ecommerce.exception.OfferAlreadyAddedException;
import com.worldpay.rascioni.ecommerce.exception.OfferNotAddedException;
import com.worldpay.rascioni.ecommerce.query.OfferDTO;

public interface OfferService {

    public List<OfferDTO> getOffers();
    
    public String addOffer(AddOfferCommand bean) throws MissingDataException, OfferAlreadyAddedException, InternalServerErrorException;

    public String removeOffer(RemoveOfferCommand bean) throws MissingDataException, OfferNotAddedException, InternalServerErrorException;

    public Float getHighestPrice();
}
