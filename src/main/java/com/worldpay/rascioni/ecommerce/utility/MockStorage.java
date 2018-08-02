package com.worldpay.rascioni.ecommerce.utility;

import java.util.ArrayList;
import java.util.List;

import com.worldpay.rascioni.ecommerce.bean.Offer;

public class MockStorage {
    private static MockStorage mock = new MockStorage();
    
    List<Offer> list = new ArrayList<>();
    
    private MockStorage() {
    }
    
    public static MockStorage getInstance() {
        return mock;
    }
    
    public List<Offer> getData(){
        return list;
    }
    
    public void addOffer(Offer bean) {
        this.list.add(bean);
    }
}
