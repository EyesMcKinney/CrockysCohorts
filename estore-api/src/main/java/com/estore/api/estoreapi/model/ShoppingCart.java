package com.estore.api.estoreapi.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A shopping cart to be given to each user
 * 
 * @author Holden Lalumiere
 */
public class ShoppingCart implements Cart{

    @JsonProperty("shopping cart") private HashMap<Integer, Integer> products;
    @JsonProperty("number of unique products") private int uniqueProducts;

    /**
     * Create a new shopping cart
     */
    public ShoppingCart(){
        this.products = new HashMap<Integer, Integer>();
        this.uniqueProducts = 0;
    }

    @Override
    /**
     * Adds a product to the cart
     * 
     * @param product the product to add
     */
    public void addProduct(Product product){
        // if the cart already has this product
        if (products.containsKey(product.getId())){
            // TODO ask whether to add to the quantity of the product, ignore it, or print a message that the item is in the cart

        }
        else{
            // add the product
            products.put(product.getId(), 1);
            uniqueProducts++;
        }
    }

    @Override
    /**
     * Removes a product from the cart
     * Precondition: the product is already in the cart
     * 
     * @param product the product to remove
     */
    public void removeProduct(Product product){
        products.remove(product.getId());
        uniqueProducts--;
    }

    @Override
    /**
     * Change the amount of the product in the cart
     * Precondition: the product is already in the cart
     * 
     * @param product the product to add or remove quantity from
     * @param amount the amount the quantity will change to
     */
    public void editProductQuantity(Product product, int amount){
        // if the quantity will be 0
        if (amount <= 0){
            // remove the product
            products.remove(product.getId());
        }
        else{
            // change the product quantity
            products.put(product.getId(), amount);
        }
    }

    @Override
    /**
     * Checks to see if an item is in stock
     * 
     * @param product the product to check if it is in stock
     * @return true if the product is out of stock, false otherwise
     */
    public boolean isProductOutOfStock(Product product){
        if(product.getQuantity() <= 0){
            //TODO make it so this product cannot be purchased (I think this can be done on the store page?)
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    /**
     * Checks if the shopping cart is empty
     * 
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty(){
        return products.isEmpty();
    }

    @Override
    /**
     * Empties the shopping cart
     */
    public void clearCart(){
        products.clear();
    }

    //TODO when wishlist is implemented
    /*
    public moveProductToWishlist(){}
        //TODO when wishlist is implemented
    }
 
    public moveAllProductsToWishlist(){
        //TODO when wishlist is implemented
    }
    */

    //TODO idk if this will be used or not
    /**
     * Gets the map of products
     * 
     * @return the map of products and their quantities
     */
    public HashMap<Integer, Integer> getProducts(){
        return products;
    }

}
