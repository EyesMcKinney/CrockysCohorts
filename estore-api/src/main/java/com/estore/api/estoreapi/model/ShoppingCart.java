package com.estore.api.estoreapi.model;

import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart {

    @JsonProperty("shopping cart") private LinkedList shoppingCart;

    public ShoppingCart(){
        this.shoppingCart = new LinkedList<>();
    }

}
