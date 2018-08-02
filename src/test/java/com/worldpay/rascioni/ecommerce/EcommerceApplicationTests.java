package com.worldpay.rascioni.ecommerce;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.worldpay.rascioni.ecommerce.repository.OfferRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceApplicationTests {

    @Autowired
    OfferRepository offerRepo;
    
	@Test
	public void emptyCollectionOK() {
	    assertTrue(offerRepo.getOffers().isEmpty());
	}

}
