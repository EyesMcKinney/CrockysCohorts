package com.estore.api.estoreapi.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A shopping cart to be given to each user
 * 
 * @author Holden Lalumiere
 */
public class ShoppingCart implements Cart{

    @JsonProperty("shopping cart") private HashMap<Integer, Integer> products;
    private InventoryDAO inventoryDAO;

    /**
     * Create a new shopping cart
     */
    public ShoppingCart(InventoryDAO inventoryDAO){
        this.products = new HashMap<Integer, Integer>();
        this.inventoryDAO = inventoryDAO;
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
            products.put(product.getId(), products.get(product.getId()) + 1);
        }
        else{
            // add the product
            products.put(product.getId(), 1);
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
    }

    @Override
    /**
     * Change the amount of the product in the cart
     * If the amount is more than there is stock it will set the amount to be the stock amount
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
            if (amount > product.getQuantity()){
                amount = product.getQuantity();
                //TODO display a message that that was too much?
            }
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

    /**
     * Buy everything currently in the cart
     * 
     * @return the total cost
     * @throws IOException
     */
    public int buyEntireCart() throws IOException{
        int total = 0;

        Iterator<Integer> iterateProducts = products.keySet().iterator();
        while (iterateProducts.hasNext()){
            int i = iterateProducts.next();
            
            // sum up the purchase
            int quantity = products.get(i);
            Product product = inventoryDAO.getProduct(i);
            total += product.getPrice() * quantity;

            // decrement the quantity from the products
            product.setQuantity(product.getQuantity() - quantity);
        }

        clearCart();
        return total;
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
