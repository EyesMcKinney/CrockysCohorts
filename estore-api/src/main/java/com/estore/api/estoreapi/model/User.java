package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User{

    @JsonProperty("username") private String username;
    @JsonProperty("shopping_cart") private ShoppingCart shoppingCart;

    public User(@JsonProperty("username") String username){
        this.username = username;
        this.shoppingCart = new ShoppingCart();
    }

    public String getUser(){
        return username;
    }

}