package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

/**
 * Implements the functionality for JSON file-based peristance for Heroes
 * 
 * {@literal Component} Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Holden Lalumiere, Tylin Hartman, Isaac McKinney, Alex Vernes, Stevie Alvarez
 */
@Component
public class InventoryFileDAO implements InventoryDAO {
    private static final Logger LOG = Logger.getLogger(InventoryFileDAO.class.getName());
    
    /**
     * Local cache of {@link Product} objects
     */
    Map<Integer, Product> products;

    /**
     * Converts between {@link Product} java objects and JSON text
     */
    private ObjectMapper objectMapper;

    /**
     * Next ID value to assign to a product
     */
    private static int currId;

    /**
     * Name of file to read from and write to
     */
    private String filename;
    
    
    public Product createProduct(Product product) throws IOException {
        // TODO Auto-generated method stub editing something
        
        return null;
    }

    
    public Product[] searchForProduct(String text) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public Product updateProduct(Product product) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public Product getProduct(int id) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public Product[] getInventory() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public boolean deleteProduct(int id) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

}
