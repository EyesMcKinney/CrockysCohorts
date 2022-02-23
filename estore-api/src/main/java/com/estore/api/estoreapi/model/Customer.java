package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer extends User{

    @JsonProperty("username") private String username;
    @JsonProperty("cart") private ShoppingCart shoppingCart;

    public Customer(String username) {
        super(username);
        this.shoppingCart = new ShoppingCart() ;
    }
    
    
}
