package com.worldpay.rascioni.ecommerce.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;
import com.worldpay.rascioni.ecommerce.utility.MockStorage;

@Repository
public class OfferRepositoryImpl implements OfferRepository {

    @Override
    public List<Offer> getOffers() {
        return MockStorage.getInstance().getData();
    }

    @Override
    public void addOffer(Offer bean) {
        MockStorage.getInstance().addOffer(bean);
        
    }

}
