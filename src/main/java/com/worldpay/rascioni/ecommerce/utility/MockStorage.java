package com.worldpay.rascioni.ecommerce.utility;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.worldpay.rascioni.ecommerce.bean.Offer;

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
    
    public Set<Offer> getData(){
        return timeMap.keySet();
    }
    
    public void addOffer(Offer bean) {
        
        LocalDateTime localDate = LocalDateTime.now().plus(bean.getExpTime(), ChronoUnit.MINUTES);
        timeMap.put(bean, localDate);
    }

    public Offer getOfferByTitle(String title) {
        for (Offer offer : timeMap.keySet()) {
            if(offer.getTitle().equals(title)) return offer;
        }
        return null;
    }

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
