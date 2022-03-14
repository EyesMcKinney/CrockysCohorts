package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;

/**
 * Interface for {@linkplain ShoppingCart ShoppingCart} object persistance
 * 
 * @author Stevie Alvarez
 */
public interface ShoppingCartDAO {
    
    /**
     * get the {@linkplain ShoppingCart ShoppingCart} of the {@linkplain User User}
     * 
     * @param id id of {@link User User} to get the {@link ShoppingCart ShoppingCart} for
     * @return User's shopping Cart 
     * 
     * @throws IOException if an issue with underlying storage
     */
    ShoppingCart getCart(int id) throws IOException;

    /**
     * Add a {@linkplain Product Product} to a {@linkplain User User}'s {@linkplain ShoppingCart ShoppingCart}.
     * 
     * @param id id of {@link User User}
     * @param product {@link Product Product} to add to {@link User User}'s {@link ShoppingCart ShoppingCart}
     * 
     * @throws IOException if an issue with underlying storage
     */
    void addToCart(int id, Product product) throws IOException;

    /**
     * Remove a {@linkplain Product Product} to a {@linkplain User User}'s {@linkplain ShoppingCart ShoppingCart}.
     * 
     * @param id id of {@link User User}
     * @param product {@link Product Product} to remove to {@link User User}'s {@link ShoppingCart ShoppingCart}
     * 
     * @throws IOException if an issue with underlying storage
     */
    void removeFromCart(int id, Product product) throws IOException;

    /**
     * Update the quanitity of a {@linkplain Product Product} in a {@linkplain User User}'s {@linkplain ShoppingCart ShoppingCart}.
     * 
     * @param id id of {@link User User}
     * @param product {@link Product Product} to update from a {@link User User}'s {@link ShoppingCart ShoppingCart}
     * @param amount the amount to update the {@link Product Product} quantity to
     * @return the updated {@link Product Product}
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product editQuantity(int id, Product product, int amount) throws IOException;

    /**
     * Clear a {@linkplain User User's} {@linkplain ShoppingCart ShoppingCart}.
     * 
     * @param id id of {@link User User}
     * 
     * @throws IOException if an issue with underlying storage
     */
    void clearCart(int id) throws IOException;

    /**
     * Purchase a {@linkplain User User's} {@linkplain ShoppingCart ShoppingCart}.
     * 
     * @param id id of {@link User User}
     * @return cost of the cart
     * 
     * @throws IOException if an issue with underlying storage
     */
    double buyCart(int id) throws IOException;

    /**
     * Get the cost of a {@linkplain User User's} {@linkplain ShoppingCart ShoppingCart}.
     * 
     * @param id id of {@link User User}
     * @return cost of the cart
     * 
     * @throws IOException if an issue with underlying storage
     */
    double getCost(int id) throws IOException;
}
