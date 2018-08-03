package com.worldpay.rascioni.ecommerce.bean;

import java.io.Serializable;
import java.util.UUID;

import com.worldpay.rascioni.ecommerce.Constants;

public class Offer implements Serializable{
    private static final long serialVersionUID = 1L;
    
    // The unique id
    private String id;
    // The title of the offer
    private String title;
    // The desc of the offer
    private String desc;
    // The price of the offer
    private Float price;
    //expiration time in second
    private Integer expTime;
    
    
    
    
    public Offer(String title, String desc, Float price, Integer expTime) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.expTime = expTime;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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



    @Override
    public String toString() {
        return "Offer [id=" + id + ", title=" + title + ", desc=" + desc + ", price=" + price + Constants.CURRENCY + ", expTime=" + expTime
                + " seconds," + "]";
    }
    
    
    
    
}
