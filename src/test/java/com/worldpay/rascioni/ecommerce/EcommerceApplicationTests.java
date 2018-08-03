package com.worldpay.rascioni.ecommerce;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;
import com.worldpay.rascioni.ecommerce.utility.MockStorage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceApplicationTests {

    @Autowired
    OfferRepository offerRepo;
    Offer offer;

    @Before
    public void init() {
        offer = new Offer("Mock", "this is a mock", 200f, 1);
    }

    /**
     * I've used BDD for this test.
     * 
     */
    @Test
    public void addElementOk() {
        Integer size = MockStorage.getInstance().getData().size();
        offerRepo.addOffer(offer);
        assertSame("Element added", size + 1, MockStorage.getInstance().getData().size());

    }

    @Test(expected = NullPointerException.class)
    public void addElementKO() {
        Offer emptyOffer = null;
        Integer size = MockStorage.getInstance().getData().size();
        offerRepo.addOffer(emptyOffer);
        assertSame("Element added", size + 1, MockStorage.getInstance().getData().size());

    }
    
    /**
     * This test fails. It shows how I apply TDD, writing the test before implementation.
     * 
     */
    @Test
    public void getHighestPriceOk() {
        offerRepo.addOffer(offer);
        assertSame("Element added", offer.getPrice(), MockStorage.getInstance().getHighestPrice());

    }

}
