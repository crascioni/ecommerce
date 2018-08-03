package com.worldpay.rascioni.ecommerce.utility;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.worldpay.rascioni.ecommerce.bean.Offer;

/**
 * This is a mock class to store the data. Offers are inside the timeMap, which has the following structure: Offer - Expiration time
 * After the initialization, a thread scans the map and removes all the expired offers.
 * @author Christian
 *
 */
public class MockStorage {
    private static MockStorage mock = new MockStorage();
    private Map<Offer, LocalDateTime> timeMap = new ConcurrentHashMap<Offer, LocalDateTime>();
    private long expiryTimeInMillis = 1000;
    
    private MockStorage() {
        initialize();
    }
    
    void initialize() {
        new CleanerT().start();
    }
    
    public static MockStorage getInstance() {
        return mock;
    }
    
    /**
     * 
     * @return the offers
     */
    public Set<Offer> getData(){
        return timeMap.keySet();
    }
    
    /**
     * Add an offer to the map
     * @param bean the offer
     */
    public void addOffer(Offer bean) {    
        LocalDateTime localDate = LocalDateTime.now().plus(bean.getExpTime(), ChronoUnit.MINUTES);
        timeMap.put(bean, localDate);
    }

    /**
     * Get the offers with this title
     * @param title name of the offer
     * @return if it exists, return the associated offers - otherwise, return null
     */
    public Offer getOfferByTitle(String title) {
        for (Offer offer : timeMap.keySet()) {
            if(offer.getTitle().equals(title)) return offer;
        }
        return null;
    }

    /**
     * remove the offer
     * @param bean
     */
    public void removeOffer(Offer bean) {
        timeMap.remove(bean);
        
    }
    
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
