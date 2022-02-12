package com.estore.api.estoreapi.controller;

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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.estore.api.estoreapi.persistence.InventoryFileDAO;
import com.estore.api.estoreapi.model.Product;


/**
 * Handles the REST API requests for the {@linkplain Product} resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Tylin Hartman, Holden Lalumiere, Alex Vernes, Isaac McKinney
 */
@RestController
@RequestMapping("products")
public class InventoryController {
    private static final Logger LOG = Logger.getLogger(InventoryController.class.getName());
    private InventoryDAO inventoryDAO;

    /**
     * 
     * @param inventoryDAO The {@link InventoryDAO Product Data Access Object} to perform CRUD operations
     * This dependency is injected by the Spring Framework
     */
    public InventoryController(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    /**
     * Responds to the GET request for a {@linkplain Product Product} for the given id
     * 
     * @param id The id used to locate the {@link Product Product}
     * 
     * @return ResponseEntity with {@link Product Product} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        LOG.info("GET /products/" + id);

        try {  // product accessed/DNE
            Product product = inventoryDAO.getProduct(id);

            if (product != null) {
                return new ResponseEntity<Product>(product, HttpStatus.OK);
                
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (IOException e) {  // storage issue
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all products which contain the given text in their name or description. 
     * @param name the text checked against all product names and descriptions. 
     * @return an array of products that have the text
     * @author Alex Vernes
     */
    public ResponseEntity<Product[]> searchforProduct(@RequestParam String name) {
        LOG.info("GET /products/?name="+name);
        try {
            return new ResponseEntity<>(inventoryDAO.searchForProduct(name), HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   /**
     * Responds to the GET request for all {@linkplain Hero heroes}
     * 
     * @author Isaac S McKinney
     * @return ResponseEntity with array of {@link Hero hero} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Product[]> getInventory() {
        LOG.info("GET /products");
        try {
            Product[] products = inventoryDAO.getInventory();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * Creates a {@linkplain Product product} with the provided product object
     * 
     * @param product - The {@link Product product} to create
     * 
     * @return ResponseEntity with created {@link Product product} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Product product} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        LOG.info("POST /heroes " + product);

        try {
            Product newProduct = inventoryDAO.createProduct(product) ;
            if(newProduct != null)
                return new ResponseEntity<>(newProduct, HttpStatus.CREATED) ;
            else
                return new ResponseEntity<>(product, HttpStatus.CONFLICT) ;
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Updates the {@linkplain Product product} with the provided {@linkplain Product product} object, if it exists
     * 
     * @param hero The {@link Product product} to update
     * 
     * @return ResponseEntity with updated {@link Product product} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * @author Holden Lalumiere
     */
    @PutMapping("")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        LOG.info("PUT /products " + product);

        try {
            if(inventoryDAO.updateProduct(product) != null){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Deletes a {@linkplain Hero hero} with the given id
     * 
     * @param id The id of the {@link Hero hero} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        LOG.info("DELETE /products/" + id);

        try {  // delete product
            boolean bool = inventoryDAO.deleteProduct(id);

            if (bool) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (IOException e) {  // storage issue
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
