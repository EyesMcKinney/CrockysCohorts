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
     * 
     * @param inventoryDAO the entire inventory
     */
    public ShoppingCart(InventoryDAO inventoryDAO){
        this.products = new HashMap<Integer, Integer>();
        this.inventoryDAO = inventoryDAO;
    }

    @Override
    /**
     * Adds a product to the cart
     * 
     * @param id the id of the {@linkplain Product product} to add
     */
    public void addProduct(int id) throws IOException{
        // if the cart already has this product
        if (inventoryDAO.getProduct(id).getId() == id) {
            if (products.containsKey(id)){
                products.put(id, products.get(id) + 1);
            }
            else{
                // add the product
                products.put(id, 1);
            }
        }
    }

    @Override
    /**
     * Removes a product from the cart
     * Precondition: the product is already in the cart
     * 
     * @param id the id of the {@linkplain Product product} to remove
     */
    public void removeProduct(int id){
        products.remove(id);
    }

    @Override
    /**
     * Change the amount of the product in the cart
     * If the amount is more than there is stock it will set the amount to be the stock amount
     * Precondition: the product is already in the cart
     * 
     * @param id the id of the {@linkplain Product product} to add or remove quantity from
     * @param amount the amount the quantity will change to
     */
    public void editProductQuantity(int id, int amount) throws IOException{
        // if the quantity will be 0
        Product product = inventoryDAO.getProduct(id);
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
     * @param id the id of the {@linkplain Product product} to check if it is in stock
     * @return true if the product is out of stock, false otherwise
     */
    public boolean isProductOutOfStock(int id) throws IOException{
        if(inventoryDAO.getProduct(id).getQuantity() <= 0){
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

    /**
     * Gets the map of products
     * 
     * @return the map of products and their quantities
     */
    public HashMap<Integer, Integer> getProducts(){
        return products;
    }
    
    /**
     * Gets the total cost of all products currently in the shopping cart
     * 
     * @return the total cost
     * @throws IOException
     * @author Alex Vernes
     */
    public int getTotalCost() throws IOException{
        int total = 0;
        Iterator<Integer> iterateProducts = products.keySet().iterator();
        while (iterateProducts.hasNext()){
            int i = iterateProducts.next();
            
            // sum up the purchase
            int quantity = products.get(i);
            Product product = inventoryDAO.getProduct(i);
            total += product.getPrice() * quantity;
        }
        return total;
    }

}
