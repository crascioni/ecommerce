package com.worldpay.rascioni.ecommerce.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.worldpay.rascioni.ecommerce.service.IdFactory;

@Service
public class IdFactoryImpl implements IdFactory{

    @Override
    public String create() {
       return UUID.randomUUID().toString();
    }

}
