package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.persistence.ShoppingCartDAO;

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

/**
 * Handles the REST API for the {@linkplain ShoppingCart} object. 
 * 
 * @author Alex Vernes
 */
@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    /**
     * Responds to the GET request for all {@linkplain Product products} in the cart
     * 
     * @param id the id of the user
     * @return ResponseEntity with array of <{@linkplain Product product}, quantity> 
     * and HTTP status of OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product[]> getProducts(@PathVariable int id) {
        LOG.info("GET /shopping-cart/" + id);
        try {
            ShoppingCart shoppingCart = shoppingCartDAO.getCart(id);
            Product[] products = shoppingCart.getProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a provided {@linkplain Product product} to {@linkplain ShoppingCart shoppingCart}
     * 
     * @param id the id of the user
     * @param product the {@linkplain Product product} to add 
     * @return ResponseEntity with added {@linkplain Product product} 
     * and HTTP status of CREATED
     */
    @PostMapping("/{id}")
    public ResponseEntity<Product> addProduct(@PathVariable int id, @RequestBody Product product) {
        LOG.info("POST /products/" + id + "/" + product.getId());
        try {
            ShoppingCart shoppingCart = shoppingCartDAO.getCart(id);
            shoppingCart.addProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove a {@linkplain Product product} from the cart
     * 
     * @param id the id of the user
     * @param product the {@linkplain Product product} removed
     * @return ResponseEntity with removed {@linkplain Product product} 
     * and HTTP status of OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> removeProduct(@PathVariable int id, @RequestBody Product product) {
        LOG.info("DELETE /prodcuts/" + id + "/" + product.getId());
        try {
            ShoppingCart shoppingCart = shoppingCartDAO.getCart(id);
            shoppingCart.removeProduct(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Updates the {@linkplain Product product} with the provided quantity
     * 
     * @param id the id of the user
     * @param product the {@linkplain Product product} to update the quantity of
     * @param amount the amount the quantity will change to
     * @return ResponseEntity with updated {@linkplain Product product}
     * and HTTP status of OK
     */
    @PutMapping("/{id}-{amount}")  // TODO: update maping, not sure if -{amount} sets amount to a param (for @RequestParam, see below) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public ResponseEntity<Product> editProductQuantity(@PathVariable int id, @RequestBody Product product, @RequestParam int amount) {  // TODO: should amount be @RequestBody
        LOG.info("PUT /products/" + product.getId() + " quantity " + amount);
        try {
            ShoppingCart shoppingCart = shoppingCartDAO.getCart(id);
            product = shoppingCart.editProductQuantity(product, amount);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Buys everything currently in the cart
     * 
     * @param id the id of the user
     * @return ResponseEntity with the total cost and HTTP status of OK
     * ReponseEntity with HTTP status of INTERNAL_SERVER_ERROR if problem with underlying storage
     */
    @DeleteMapping("")
    public ResponseEntity<Double> buyEntireCart(@PathVariable int id) {
        LOG.info("Delete /products");
        try {
            ShoppingCart shoppingCart = shoppingCartDAO.getCart(id);
            double total = shoppingCart.buyEntireCart();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for the total cost of the {@linkplain ShoppingCart shoppingCart}
     * 
     * @param id the id of the user
     * @return ResponseEntity with the total cost of the {@linkplain ShoppingCart shoppingCart} 
     * and HTTP status of OK
     */
    @GetMapping("cost")
    public ResponseEntity<Double> getTotalCost(@PathVariable int id) {
        LOG.info("GET cost");
        try {
            ShoppingCart shoppingCart = shoppingCartDAO.getCart(id);
            double total = shoppingCart.getTotalCost();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
