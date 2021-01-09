package com.hfad.binusezyfoody.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartData {
    private static String name;
    private static String imageUrl;
    private static String rating;
    private static String price;
    private static int qty;

    public CartData(String name, String imageUrl, String rating, String price, int qty) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
