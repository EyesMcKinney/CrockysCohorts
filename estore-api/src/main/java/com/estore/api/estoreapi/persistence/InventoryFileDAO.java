package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
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
    private ObjectMapper oMapper;

    /**
     * Next ID value to assign to a product
     */
    private static int currId;

    /**
     * Name of file to read from and write to
     */
    private String filename;

    public InventoryFileDAO(@Value("${products.file}") String filename, ObjectMapper oMapper) throws IOException {
        this.filename = filename;
        this.oMapper = oMapper;
        //load();
    }
    
    /**
     * {@inheritDoc}
     * 
     * @author Tylin Hartman
     */
    public Product createProduct(Product product) throws IOException {
        synchronized(products) {
            // We create a new hero object because the id field is immutable
            // and we need to assign the next unique id
            Product newProduct = new Product(nextId(),product.getName());
            products.put(newProduct.getId(),newProduct);
            save(); // may throw an IOException
            return newProduct;
        }
    }

    
    public Product[] searchForProduct(String text) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public Product updateProduct(Product product) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @author Stevie Alvarez
     */
    @Override
    public Product getProduct(int id) throws IOException {
        synchronized(products) {
            return products.get(id);  // Recall: Map.get(s) returns null if s not present
        }
    }

    
    public Product[] getInventory() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @author Stevie Alvarez
     */
    public boolean deleteProduct(int id) throws IOException {
        synchronized(products) {
            if (products.containsKey(id)) {
                products.remove(id);
                return save();
            
            } else {
                return false;
            }

        }
    }

}
