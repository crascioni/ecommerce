package com.worldpay.rascioni.ecommerce;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.command.AddOfferCommand;
import com.worldpay.rascioni.ecommerce.command.RemoveOfferCommand;
import com.worldpay.rascioni.ecommerce.exception.InternalServerErrorException;
import com.worldpay.rascioni.ecommerce.exception.MissingDataException;
import com.worldpay.rascioni.ecommerce.exception.OfferAlreadyAddedException;
import com.worldpay.rascioni.ecommerce.exception.OfferNotAddedException;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;
import com.worldpay.rascioni.ecommerce.service.IdFactory;
import com.worldpay.rascioni.ecommerce.service.OfferService;
/**
 * This class executes ServiceImpl's functional tests, so there aren't mock objects. 
 * @author Christian
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceApplicationTests {

    @Autowired
    OfferRepository offerRepo;

    @Autowired
    OfferService offerService;

    @Autowired
    IdFactory idFactory;

    Offer offer;

    @Before
    public void setup() {
        offer = new Offer(idFactory.create(), "Mock", "this is a mock", 200f, 1);
    }

    /**
     * Checks the input and throws a MissingDataException
     * @throws MissingDataException
     * @throws OfferAlreadyAddedException
     * @throws InternalServerErrorException
     */
    @Test(expected = MissingDataException.class)
    public void addOfferBadRequest() throws MissingDataException, OfferAlreadyAddedException, InternalServerErrorException {
        AddOfferCommand emptyOffer = new AddOfferCommand();
        
        offerService.addOffer(emptyOffer);

    }

    /**
     * Checks the data and throws a OfferAlreadyAddedException
     * @throws MissingDataException
     * @throws OfferAlreadyAddedException
     * @throws InternalServerErrorException
     */
    @Test(expected = OfferAlreadyAddedException.class)
    public void addOfferWithSameTitle() throws MissingDataException, OfferAlreadyAddedException, InternalServerErrorException {
        AddOfferCommand offerCommand = new AddOfferCommand();
        offerCommand.setTitle(offer.getTitle());
        offerCommand.setPrice(offer.getPrice());
        offerCommand.setExpTime(offer.getExpTime());
        offerRepo.addOffer(offer);

        offerService.addOffer(offerCommand);
    }

    /**
     * This test fails. It shows how I apply TDD, writing the test before
     * implementation.
     * @throws InternalServerErrorException 
     * @throws OfferAlreadyAddedException 
     * @throws MissingDataException 
     * 
     */
    @Test
    public void getHighestPriceRepoOk() throws MissingDataException, OfferAlreadyAddedException, InternalServerErrorException {
        AddOfferCommand offerCommand = new AddOfferCommand();
        offerCommand.setTitle("Mock");
        offerCommand.setPrice(200f);
        offerCommand.setExpTime(1);
        offerService.addOffer(offerCommand);

        assertSame("Element added", offerCommand.getPrice(), offerService.getHighestPrice());

    }

    /**
     * Removes an offer from the repository
     * @throws MissingDataException
     * @throws OfferAlreadyAddedException
     * @throws InternalServerErrorException
     * @throws OfferNotAddedException
     */
    @Test
    public void removeOfferRepoOk() throws MissingDataException, OfferAlreadyAddedException,
            InternalServerErrorException, OfferNotAddedException {
        AddOfferCommand offerCommand = new AddOfferCommand();
        offerCommand.setTitle(offer.getTitle());
        offerCommand.setPrice(offer.getPrice());
        offerCommand.setExpTime(offer.getExpTime());
        RemoveOfferCommand remove = new RemoveOfferCommand();
        remove.setTitle(offer.getTitle());
        Integer size = offerService.getOffers().size();

        offerService.addOffer(offerCommand);
        offerService.removeOffer(remove);

        assertSame("Element added", size, offerService.getOffers().size());

    }

}
