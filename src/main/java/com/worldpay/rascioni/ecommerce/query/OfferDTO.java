package com.worldpay.rascioni.ecommerce.query;

public class OfferDTO {
 // the title of the offer
    private String title;
    // the desc of the offer
    private String desc;
    // the price of the offer
    private String price;
    //expiration time in min
    private String expTime;
    
    
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getExpTime() {
        return expTime;
    }
    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }

}
