package com.estore.api.estoreapi.model;

/**
 * The Cart interface for Shopping Cart and Wishlist
 * 
 * @author Holden Lalumiere
 */
public interface Cart {

    /**
     * Add a product to the cart
     * 
     * @param product the product to add
     */
    public void addProduct(Product product);

    /**
     * Remove a product from the cart
     * Precondition: the product is already in the cart
     * 
     * @param product the product to remove
     */
    public void removeProduct(Product product);

    /**
     * Change the product quantity in the cart
     * Precondition: the product is already in the cart
     * 
     * @param product the product to change the quantity of
     * @param amount the quantity to change to
     */
    public void editProductQuantity(Product product, int amount);

    /**
     * Remove all items from the cart
     */
    public void clearCart();

    /**
     * Check if the product is in stock
     * 
     * @param  the product to check
     * @return true if the product is out of stock, false otherwise
     */
    public boolean isProductOutOfStock(Product product);

    /**
     * Check if the cart is empty
     * 
     * @return true if the cart is empty, false otherwise
     */
    public boolean isEmpty();

}
