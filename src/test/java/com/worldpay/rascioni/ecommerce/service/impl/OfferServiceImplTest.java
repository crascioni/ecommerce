package com.worldpay.rascioni.ecommerce.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.worldpay.rascioni.ecommerce.Constants;
import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.exception.InternalServerErrorException;
import com.worldpay.rascioni.ecommerce.exception.MissingDataException;
import com.worldpay.rascioni.ecommerce.exception.OfferAlreadyAddedException;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;

public class OfferServiceImplTest {
    OfferRepository repo;
    OfferServiceImpl service;

    @Before
    public void setUp() throws Exception {
        repo = Mockito.mock(OfferRepository.class);
        service = new OfferServiceImpl(repo, () -> "1");
    }

    @Test
    public void addOfferOk() throws MissingDataException, OfferAlreadyAddedException, InternalServerErrorException {
        AddOfferCommand offer = new AddOfferCommand();
        offer.setTitle("Mock");
        offer.setPrice(200f);
        offer.setExpTime(1);
        Offer bean = new Offer("1", offer.getTitle(), null, offer.getPrice(), offer.getExpTime());

        String response = service.addOffer(offer);

        assertEquals(Constants.INSERT_OK, response);
        Mockito.verify(repo).addOffer(bean);
    }

    @Test(expected = OfferAlreadyAddedException.class)
    public void addOfferWithSameTitle()
            throws MissingDataException, OfferAlreadyAddedException, InternalServerErrorException {
        Offer bean = new Offer("1", "Mock", null, 1f, 1);
        Mockito.when(repo.findByTitle("Mock")).thenReturn(bean);
        AddOfferCommand offer = new AddOfferCommand();
        offer.setTitle("Mock");
        offer.setPrice(200f);
        offer.setExpTime(1);

        String response = service.addOffer(offer);
        System.out.println(response);
        Mockito.verify(repo).addOffer(bean);
        
        assertEquals(Constants.ALREADY_ADDED_ERROR, response);

    }
}
