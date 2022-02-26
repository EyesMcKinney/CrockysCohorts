package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.logging.Logger;

public class User{
    private static final Logger LOG = Logger.getLogger(User.class.getName());

    @JsonProperty("username") private String username;
    @JsonProperty("shoppingCart") private ShoppingCart shoppingCart;

    static final String STRING_FORMAT = "User [username=%s, shoppingCart=%s]" ;

    public User(@JsonProperty("username") String username){
        this.username = username;
        this.shoppingCart = new ShoppingCart();
    }

    public String getName(){
        return username;
    }

    public ShoppingCart getCart(){
        return shoppingCart;
    }

    public Boolean addToCart(int id){
        this.shoppingCart.addProduct(id);
        return true;
    }

    public Boolean removeFromCart(int id){
        this.shoppingCart.removeProduct(id);
        return true;
    }
}