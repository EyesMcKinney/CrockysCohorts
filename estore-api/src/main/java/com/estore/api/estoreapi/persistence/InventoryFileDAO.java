package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;

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

    
    public Product createProduct(Product product) throws IOException {
        // TODO Auto-generated method stub
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
