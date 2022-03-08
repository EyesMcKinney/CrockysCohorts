package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.User;

/**
 * @author Isaac S McKinney
 * @author Stevie Alvarez
 */

public interface UserDAO {

    /**
     * Retrieve a {@linkplain User user} with the provided username.
     * 
     * @param username The name identifying the desired {@link User user}
     * @return A {@link User user} with the matching username,
     * null if {@link User user} could not be found
     * @throws IOException If issue with underlying storage
     * 
     * @author Stevie Alvarez
     */
    User getUser(String username) throws IOException;
    
    /**
     * Creates and saves a {@linkplain User user}
     * 
     * @param user {@linkplain User user} object to be created and saved
     * 
     * @return new {@link User user} if successful, null otherwise
     * 
     * @throws IOException if an issue with underlying storage
     */
    User createUser(User user) throws IOException;

    /**
     * Find all {@linkplain User user} which contain the given text in their name
     * 
     * @param text The text to match against
     * 
     * @return An array of {@link User user} whose names contain the given text,
     * may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    User[] findUsers(String text) throws IOException;

    /**
     * Retrieves all {@linkplain User user}
     * 
     * @return An array of {@link User user} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    User[] getUsers() throws IOException;

    /**
     * get the shopping cart of the user
     * 
     * @param user user to get the shopping cart for
     * 
     * @return User's shopping Cart 
     * 
     * @throws IOException
     */
    ShoppingCart getCart(User user) throws IOException;

    /**
     * Add a {@linkplain Product Product} to a {@linkplain User User}'s cart.
     * 
     * @throws IOException if an issue with underlying storage
     */
    void addToCart(User user, Product product) throws IOException;

    /**
     * Remove a {@linkplain Product Product} to a {@linkplain User User}'s cart.
     * 
     * @throws IOException if an issue with underlying storage
     */
    void removeFromCart(User user, Product product) throws IOException;
}
