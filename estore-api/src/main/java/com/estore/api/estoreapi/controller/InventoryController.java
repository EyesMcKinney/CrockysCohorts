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
}
