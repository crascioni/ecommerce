package com.worldpay.rascioni.ecommerce.command;

import java.io.Serializable;

public class AddOfferCommand implements Serializable{
    private static final long serialVersionUID = 1L;
    
    // the title of the offer
    private String title;
    // the desc of the offer
    private String desc;
    // the price of the offer
    private Float price;
    //expiration time in min
    private Integer expTime;
    
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Integer getExpTime() {
        return expTime;
    }
    public void setExpTime(Integer expTime) {
        this.expTime = expTime;
    }
    
    
}
