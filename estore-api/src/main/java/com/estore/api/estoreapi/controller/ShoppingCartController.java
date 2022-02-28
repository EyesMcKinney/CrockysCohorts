package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.HashMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Handles the REST API for the {@linkplain ShoppingCart} object. 
 * 
 * @author Alex Vernes
 */
public class ShoppingCartController {
    private ShoppingCart shoppingCart;


    public ShoppingCartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Gets the map of products
     * 
     * @return ResponseEntity HTTP status of OK
     */
    public ResponseEntity<HashMap<Integer,Integer>> getProducts() {
        // TODO ADV check if this is the information we want in order to display a shopping cart
        HashMap<Integer, Integer> products = shoppingCart.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Adds a product to the cart
     * 
     * @param product the product to add 
     * @return ResponseEntity HTTP status of OK
     */
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        shoppingCart.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Remove a product from the cart
     * 
     * @param product the product removed
     * @return ResponseEntity HTTP status of OK
     */
    public ResponseEntity<Product> removeProduct(@RequestBody Product product) {
        // TODO ADV change to use IDs instead of direct Product objects???
        shoppingCart.removeProduct(product);
        // Can't check if product is already in cart, therefore...
        // TODO ADV change precondition for ShoppingCart removeProduct()???
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Change the amount of the product in the cart 
     * If the amount is more than there is in stock, 
     * it will set the amount to the stock amount
     * 
     * @param product the product to add or remove quantity from
     * @param amount the amount the auantity will change to
     * @return ResponseEntity HTTP status of OK
     */
    public ResponseEntity<Product> editProductQuantity(Product product, int amount) {
        // TODO ADV change to product IDs???
        shoppingCart.editProductQuantity(product, amount);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Buys everything currently in the cart
     * 
     * @return the total cost
     */
    public ResponseEntity<Integer> buyEntireCart() {
        // ADV Do we need this???
        try {
            int total = shoppingCart.buyEntireCart();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
