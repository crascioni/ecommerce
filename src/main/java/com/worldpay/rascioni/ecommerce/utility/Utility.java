package com.worldpay.rascioni.ecommerce.utility;

import com.worldpay.rascioni.ecommerce.bean.Offer;

public class Utility {

        public static boolean isNullOrEmpty(String string) {
            if(string == null || string == "") return true;
            return false;
        }
      
        
        /**
         * Check if an offer with this name is already inside the collection
         * @param title the name of the new offer
         * @return if present, return the offer
         */
        public static Offer isPresent(String title) {
            for(Offer offer: MockStorage.getInstance().getData()) {
                if(offer.getTitle().equals(title)) { 
                    return offer;
                }
            }
            return null;
        }
}
