package com.worldpay.rascioni.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldpay.rascioni.ecommerce.Constants;
import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.exception.InternalServerErrorException;
import com.worldpay.rascioni.ecommerce.exception.MissingDataException;
import com.worldpay.rascioni.ecommerce.exception.OfferAlreadyAddedException;
import com.worldpay.rascioni.ecommerce.exception.OfferNotAddedException;
import com.worldpay.rascioni.ecommerce.query.OfferDTO;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;
import com.worldpay.rascioni.ecommerce.service.IdFactory;
import com.worldpay.rascioni.ecommerce.service.OfferService;
import com.worldpay.rascioni.ecommerce.utility.Utility;

@Service
public class OfferServiceImpl implements OfferService {
    

    private final OfferRepository offerRepository;
    private final IdFactory idFactory;
    
    
    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, IdFactory idFactory) {
        super();
        this.offerRepository = offerRepository;
        this.idFactory = idFactory;
    }

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
    public String addOffer(AddOfferCommand bean) throws MissingDataException, OfferAlreadyAddedException, InternalServerErrorException{
        //Check if the input is null
        if(Utility.isNullOrEmpty(bean.getTitle()) || bean.getPrice() == 0 || bean.getExpTime() == 0) {
            throw new MissingDataException(Constants.MISSING_DATA_ERROR);
        }
        //Check if the bean has already been added
        if(offerRepository.findByTitle(bean.getTitle()) != null) {
            throw new OfferAlreadyAddedException(Constants.ALREADY_ADDED_ERROR);
        }       
        Offer offer = new  Offer(
                idFactory.create(),
                bean.getTitle(), 
                bean.getDesc(), 
                bean.getPrice(), 
                bean.getExpTime()
                );
     
        try {
            offerRepository.addOffer(offer);
        }catch(Exception e) {
            throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR);
        }      
        return Constants.INSERT_OK;
    }

    @Override
    public String removeOffer(RemoveOfferCommand bean) throws MissingDataException, OfferNotAddedException, InternalServerErrorException {
        // Check if the input is null
        if (Utility.isNullOrEmpty(bean.getTitle())) {
            throw new MissingDataException(Constants.MISSING_DATA_ERROR);
        }
        // Check if the bean is in the collection
        Offer offer = offerRepository.findByTitle(bean.getTitle());
        if (offer == null) {
            throw new OfferNotAddedException(Constants.MISSING_ELEMENT_ERROR);
        }
        try {
            offerRepository.removeOffer(offer);
        } catch (Exception e) {
            throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR);
        }
        return Constants.DELETE_OK;
    }

    @Override
    public Float getHighestPrice() {
        return offerRepository.getHighestPrice();
    }

}
