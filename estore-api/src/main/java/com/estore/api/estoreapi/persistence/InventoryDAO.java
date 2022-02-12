package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;

/**
 * Defines the interface for {@linkplain Product product} object persistence
 * 
 * @author Holden Lalumiere, Tylin Hartman, Isaac McKinney, Alex Vernes, Stevie Alvarez
 */
public interface InventoryDAO {

    /**
     * Creates and saves a {@linkplain Product product}
     * 
     * @param product {@linkplain Product product} object to be created and saved
     * 
     * @return new {@link Product product} if successful, null otherwise
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product createProduct(Product product) throws IOException;
    
    /**
     * Find all {@linkplain Product product} which contain the given text in their name
     * 
     * @param text The text to match against
     * 
     * @return An array of {@link Product products} whose names contain the given text,
     * may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product[] findProducts(String text) throws IOException;
    
    /**
     * Updates and saves a {@linkplain Product product}
     * 
     * @param product {@link Product product} object to be updated and saved
     * 
     * @return updated {@link Product product} if successful, null if 
     * {@link Product product} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Product updateProduct(Product product) throws IOException;
    
    /**
     * Retrieves a {@linkplain Product product} with the given id
     * 
     * @param id The id of the {@link Product product} to get
     * 
     * @return a {@link Product product} object with the matching id, 
     * null if {@link Product product} could not be found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product getProduct(int id) throws IOException;
    
    /**
     * Retrieves all {@linkplain Product products}
     * 
     * @return An array of {@link Product product} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product[] getInventory() throws IOException;
    
    /**
     * Deletes a {@linkplain Product product} with the given id
     * 
     * @param id The id of the {@link Product product}
     * 
     * @return true if the {@link Product product} was deleted,
     * false if {@link Product product} with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteProduct(int id) throws IOException;
}
