package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.HashMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

/**
 * Handles the REST API for the {@linkplain ShoppingCart} object. 
 * 
 * @author Alex Vernes
 */
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCart shoppingCart;


    public ShoppingCartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Responds to the GET request for all {@linkplain Product products} in the cart
     * 
     * @return ResponseEntity with HashMap of <{@linkplain Product product}, quantity> 
     * and HTTP status of OK
     */
    @GetMapping("")
    public ResponseEntity<HashMap<Integer,Integer>> getProducts() {
        LOG.info("GET /products");
        // TODO ADV check if this is the information we want in order to display a shopping cart
        HashMap<Integer, Integer> products = shoppingCart.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Adds a provided {@linkplain Product product} to {@linkplain ShoppingCart shoppingCart}
     * 
     * @param product the {@linkplain Product product} to add 
     * 
     * @return ResponseEntity with added {@linkplain Product product} 
     * and HTTP status of CREATED
     */
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        LOG.info("POST /products " + product);
        shoppingCart.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Remove a {@linkplain Product product} from the cart
     * 
     * @param product the {@linkplain Product product} removed
     * 
     * @return ResponseEntity with removed {@linkplain Product product} 
     * and HTTP status of OK
     */
    @DeleteMapping("")
    // TODO ADV need id of product for DeleteMapping
    public ResponseEntity<Product> removeProduct(@RequestBody Product product) {
        LOG.info("DELETE /prodcuts/");
        // TODO ADV change to use IDs instead of direct Product objects???
        shoppingCart.removeProduct(product);
        // Can't check if product is already in cart, therefore...
        // TODO ADV change precondition for ShoppingCart removeProduct()???
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Updates the {@linkplain Product product} with the provided quantity
     * 
     * @param product the {@linkplain Product product} to update the quantity of
     * @param amount the amount the quantity will change to
     * @return ResponseEntity with HTTP status of OK
     */
    @PutMapping("")
    public ResponseEntity<Product> editProductQuantity(@RequestBody Product product, @RequestParam int amount) {
        LOG.info("PUT /products " + product + " quantity " + amount);
        // TODO ADV change to product IDs???
        // ADV I can't check the existance of the product in the ShoppingCart from out here
        // so the precondition might have to change
        shoppingCart.editProductQuantity(product, amount);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Buys everything currently in the cart
     * 
     * @return ResponseEntity with the total cost and HTTP status of OK
     * ReponseEntity with HTTP status of INTERNAL_SERVER_ERROR if problem with underlying storage
     */
    @DeleteMapping("")
    public ResponseEntity<Integer> buyEntireCart() {
        LOG.info("Delete /products");
        // ADV Do we need this???
        try {
            int total = shoppingCart.buyEntireCart();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for the total cost of the {@linkplain ShoppingCart shoppingCart}
     * 
     * @return ResponseEntity with the total cost of the {@linkplain ShoppingCart shoppingCart} 
     * and HTTP status of OK
     */
    @GetMapping("")
    public ResponseEntity<Integer> getTotalCost() {
        LOG.info("GET cost");
        try {
            int total = shoppingCart.getTotalCost();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
