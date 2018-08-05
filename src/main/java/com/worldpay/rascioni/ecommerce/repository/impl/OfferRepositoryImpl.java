package com.worldpay.rascioni.ecommerce.repository.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.worldpay.rascioni.ecommerce.bean.Offer;
import com.worldpay.rascioni.ecommerce.repository.OfferRepository;


@Repository
public class OfferRepositoryImpl implements OfferRepository {
    //This is the mock where all the date are stored.
    private Map<Offer, LocalDateTime> timeMap = new ConcurrentHashMap<Offer, LocalDateTime>();
    private long expiryTimeInMillis = 1000;
    
    
    
    public OfferRepositoryImpl() {
        initialize();
    }
    
    void initialize() {
        new CleanerT().start();
    }

    @Override
    public Set<Offer> getOffers() {
        return timeMap.keySet();
    }

    @Override
    public void addOffer(Offer bean) {
        LocalDateTime localDate = LocalDateTime.now().plus(bean.getExpTime(), ChronoUnit.MINUTES);
        timeMap.put(bean, localDate);
        
    }

    @Override
    public void removeOffer(Offer bean) {
        timeMap.remove(bean); 
        
    }

    @Override
    public Offer findByTitle(String title) {
        for (Offer offer : timeMap.keySet()) {
            if(offer.getTitle().equals(title)) return offer;
        }
        return null;
    }
    
    @Override
    public Float getHighestPrice() {
        // TODO This method is empty. I've tested it before writing the behavior.
        return null;
    }

    /**
     * This class takes care of checking when an item has expired and removing it from the map.
     * @author Christian
     *
     */
    class CleanerT extends Thread {
        @Override
        public void run() {
            System.out.println("Initiating Cleaner Thread..");
            while (true) {
                cleanMap();
                try {
                    Thread.sleep(expiryTimeInMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Scan the map and remove the expired elements.
         */
        private void cleanMap() {
            LocalDateTime localDate = LocalDateTime.now();
            for (Offer key : timeMap.keySet()) {
                if (localDate.isAfter(timeMap.get(key))) {
                    System.out.println("LocalDate " + localDate + " > " + timeMap.get(key));
                    timeMap.remove(key);
                }
            }
        }
    }
}
