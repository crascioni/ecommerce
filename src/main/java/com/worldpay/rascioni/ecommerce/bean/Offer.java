package com.worldpay.rascioni.ecommerce.bean;

import java.io.Serializable;

import com.worldpay.rascioni.ecommerce.Constants;

public class Offer implements Serializable {
    private static final long serialVersionUID = 1L;

    // The unique id
    private String id;
    // The title of the offer
    private String title;
    // The desc of the offer
    private String desc;
    // The price of the offer
    private Float price;
    // expiration time in second
    private Integer expTime;

    public Offer() {
    }

    public Offer(String id, String title, String desc, Float price, Integer expTime) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.expTime = expTime;
    }

    public String getId() {
        return id;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + ((expTime == null) ? 0 : expTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Offer other = (Offer) obj;
        if (desc == null) {
            if (other.desc != null)
                return false;
        } else if (!desc.equals(other.desc))
            return false;
        if (expTime == null) {
            if (other.expTime != null)
                return false;
        } else if (!expTime.equals(other.expTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Offer [id=" + id + ", title=" + title + ", desc=" + desc + ", price=" + price + Constants.CURRENCY
                + ", expTime=" + expTime + " seconds," + "]";
    }

}
