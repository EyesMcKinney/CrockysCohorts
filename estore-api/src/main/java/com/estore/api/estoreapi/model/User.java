package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.logging.Logger;

/**
 * @author Isaac S McKinney
 */
public class User{
    private static final Logger LOG = Logger.getLogger(User.class.getName());

    @JsonProperty("username") private String username;
    @JsonProperty("shoppingCart") private ShoppingCart shoppingCart;

    static final String STRING_FORMAT = "User [username=%s, shoppingCart=%s]" ;

    /**
     * Constructor for the user object
     * @param username - user name to be given to the user
     */
    public User(@JsonProperty("username") String username){
        this.username = username;
        this.shoppingCart = new ShoppingCart();
    }

    /**
     * get the user name of the user
     * @return the users username
     */
    public String getName(){
        return username;
    }

    /**
     * get the shopping cart of the user
     * @return the users cart
     */
    public ShoppingCart getCart(){
        return shoppingCart;
    }

    /**
     * add an item to the cart
     * @param id - id of the item to add
     * @return - true on success
     */
    public Boolean addToCart(int id){
        return this.shoppingCart.addProduct(id);
    }

    /**
     * remove an item to the cart
     * @param id - id of the item to add
     * @return - true on success
     */
    public Boolean removeFromCart(int id){
        return this.shoppingCart.removeProduct(id);
    }

    /**
     * for testing purposes, puts the user into a readable format
     */
    @Override
    public String toString(){
        return "User: " + this.username + ", Cart : " + this.shoppingCart;
    }
}