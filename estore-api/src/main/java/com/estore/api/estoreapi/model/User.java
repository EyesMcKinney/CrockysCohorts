package com.estore.api.estoreapi.model;

import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Isaac S McKinney
 */
public class User{
    private static final Logger LOG = Logger.getLogger(User.class.getName());

    @JsonProperty("username") private String username;
    @JsonProperty("id") private int id;
    private ShoppingCart shoppingCart;

    static final String STRING_FORMAT = "User [id=%d, username=%s]" ;

    /**
     * Constructor for the user object
     * @param username - user name to be given to the user
     */
    public User(@JsonProperty("id") int id, @JsonProperty("username") String username){
        this.id = id;
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
     * @param product the item to add
     * @return - true on success
     */
    public void addToCart(Product product) throws IOException {
        this.shoppingCart.addProduct(product);
    }

    /**
     * remove an item to the cart
     * @param product the item to add
     * @return - true on success
     */
    public void removeFromCart(Product product){
        this.shoppingCart.removeProduct(product);
    }

    /**
     * for testing purposes, puts the user into a readable format
     */
    @Override
    public String toString(){
        return String.format(STRING_FORMAT, id, username);
    }
}