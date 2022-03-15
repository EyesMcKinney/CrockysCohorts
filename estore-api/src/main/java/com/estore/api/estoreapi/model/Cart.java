package com.estore.api.estoreapi.model;

import java.io.IOException;

/**
 * The Cart interface for Shopping Cart and Wishlist
 * 
 * @author Holden Lalumiere
 */
public interface Cart {

    /**
     * Get the user's id from the cart
     * 
     * @returns the user's id
     */
    public int getId();

    /**
     * Add a product to the cart
     * 
     * @param product the product to add
     * @throws IOException if an issue with underlying storage
     */
    public void addProduct(Product product) throws IOException;

    /**
     * Remove a product from the cart
     * Precondition: the product is already in the cart
     * 
     * @param product the product to remove
     * @throws IOException if an issue with underlying storage
     */
    public void removeProduct(Product product) throws IOException;

    /**
     * Change the product quantity in the cart
     * Precondition: the product is already in the cart
     * 
     * @param product the product to change the quantity of
     * @param amount the quantity to change to
     * @throws IOException if an issue with underlying storage
     * @return the {@link Product Product} updated
     */
    public Product editProductQuantity(Product product, int amount) throws IOException;

    /**
     * Remove all items from the cart
     */
    public void clearCart();

    /**
     * Check if the cart is empty
     * 
     * @return true if the cart is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Gets the array of products
     * 
     * @return the array of products and their quantities
     */
    public Product[] getProducts();

}
