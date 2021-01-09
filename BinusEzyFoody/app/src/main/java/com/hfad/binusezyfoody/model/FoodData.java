
package com.hfad.binusezyfoody.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodData {

    @SerializedName("popular")
    @Expose
    private List<Popular> popular = null;


    @SerializedName("allmenu")
    @Expose
    private List<Allmenu> allmenu = null;

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;

    @SerializedName("snacks")
    @Expose
    private List<Snack> snacks = null;

    @SerializedName("carts")
    @Expose
    private List<CartData> carts = null;


    public List<Popular> getPopular() {
        return popular;
    }
    public void setPopular(List<Popular> popular) {
        this.popular = popular;
    }


    public List<Allmenu> getAllmenu() {
        return allmenu;
    }
    public void setAllmenu(List<Allmenu> allmenu) {
        this.allmenu = allmenu;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }
    public void setSnacks(List<Snack> snacks) {
        this.snacks = snacks;
    }

    public List<CartData> getCart() {
        return carts;
    }
    public void setCart(List<CartData> carts) {
        this.carts = carts;
    }





}
