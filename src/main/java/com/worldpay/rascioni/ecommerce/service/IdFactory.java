package com.worldpay.rascioni.ecommerce.service;

/**
 * This class provides to return a new Id. I used the interface to make it easier to mock and test.
 * @author Christian
 *
 */
public interface IdFactory {
    String create();
}
