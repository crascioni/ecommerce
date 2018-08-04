package com.worldpay.rascioni.ecommerce.exception;

public class OfferAlreadyAddedException extends Exception {
    private static final long serialVersionUID = 1L;

    public OfferAlreadyAddedException(String message) {
        super(message);
    }
    
    
}
