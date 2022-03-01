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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Handles the REST API for the {@linkplain ShoppingCart} object. 
 * 
 * @author Alex Vernes
 */
@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCart shoppingCart;


    // TODO change ShoppingCart to UserDAO stuff 
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
        HashMap<Integer, Integer> products = shoppingCart.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Adds a provided {@linkplain Product product} to {@linkplain ShoppingCart shoppingCart}
     * 
     * @param id the ID of the {@linkplain Product product} to add 
     * 
     * @return ResponseEntity with added {@linkplain Product product} 
     * and HTTP status of CREATED
     */
    @PostMapping("/{id}")
    public ResponseEntity<Integer> addProduct(@PathVariable int id) {
        LOG.info("POST /products " + id);
        try {
            shoppingCart.addProduct(id);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove a {@linkplain Product product} from the cart
     * 
     * @param id the ID of the {@linkplain Product product} removed
     * 
     * @return ResponseEntity with removed {@linkplain Product product} 
     * and HTTP status of OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> removeProduct(@PathVariable int id) {
        LOG.info("DELETE /prodcuts/");
        shoppingCart.removeProduct(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Updates the {@linkplain Product product} with the provided quantity
     * 
     * @param id the ID of the {@linkplain Product product} to update the quantity of
     * @param amount the amount the quantity will change to
     * @return ResponseEntity with HTTP status of OK
     */
    @PutMapping("/{id}-{amount}")
    public ResponseEntity<Integer> editProductQuantity(@PathVariable int id, @RequestParam int amount) {
        LOG.info("PUT /products " + id + " quantity " + amount);

        try {
            shoppingCart.editProductQuantity(id, amount);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
