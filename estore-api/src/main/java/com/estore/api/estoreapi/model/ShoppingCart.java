package com.estore.api.estoreapi.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A shopping cart to be given to each user
 * 
 * @author Holden Lalumiere
 */
public class ShoppingCart implements Cart{

    @JsonProperty("shopping-cart")List<Product> products;

    /**
     * Create a new shopping cart
     */
    public ShoppingCart(){  // TODO: load shopping cart from file w/r/t user id
        this.products = new ArrayList<>();
        load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProduct(Product product) throws IOException{
        // if the cart already has this product
        int i = products.indexOf(product);
        product.setQuantity(1);
        if (i != -1){
            product = products.get(i);
            product.setQuantity(product.getQuantity() + 1);
        }
        else{
            // add the product
            products.add(product);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProduct(Product product){
        products.remove(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product editProductQuantity(Product product, int amount) throws IOException{
        int i = products.indexOf(product);
        product.setQuantity(0);

        // if the quantity will be 0
        if (amount <= 0){
            // remove the product
            products.remove(product);
        }
        else if (i != -1) {
            product = products.get(i);
            product.setQuantity(amount);
        }

        return product;
    }

    /**
     * Checks if the shopping cart is empty
     * 
     * @return true if it is empty, false otherwise
     */
    @Override
    public boolean isEmpty(){
        return products.isEmpty();
    }

    /**
     * Empties the shopping cart
     */
    @Override
    public void clearCart(){
        products.clear();
    }

    /**
     * Buy everything currently in the cart
     * 
     * @return the total cost
     * @throws IOException
     */
    public double buyEntireCart() throws IOException{
        double total = 0;
        Product product;

        Iterator<Product> iterateProducts = products.iterator();
        while (iterateProducts.hasNext()){
            product = iterateProducts.next();
            
            // sum up the purchase
            total += product.getPrice() * product.getQuantity();
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
     * {@inheritDoc}
     */
    public Product[] getProducts(){
        return (Product[])products.toArray();
    }
    
    /**
     * Gets the total cost of all products currently in the shopping cart
     * 
     * @return the total cost
     * @throws IOException
     * @author Alex Vernes
     */
    public double getTotalCost() throws IOException{
        double total = 0;
        Iterator<Product> iterateProducts = products.iterator();
        Product product;

        while (iterateProducts.hasNext()){
            product = iterateProducts.next();
            
            // sum up the purchase
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    /**
     * Load a {@linkplain User User}'s {@linkplain ShoppingCart ShoppingCart} w/r/t user id.
     */
    private void load() {
        // TODO
    }

    /**
     * Load a {@linkplain User User}'s {@linkplain ShoppingCart ShoppingCart} w/r/t user id.
     */
    private void save() {
        // TODO
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
