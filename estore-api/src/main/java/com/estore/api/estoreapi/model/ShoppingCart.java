package com.estore.api.estoreapi.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A shopping cart to be given to each user
 * 
 * @author Holden Lalumiere
 * @author Stevie Alvarez
 */
public class ShoppingCart implements Cart{
    private static final Logger LOG = Logger.getLogger(ShoppingCart.class.getName());

    @JsonProperty("id")int id;
    @JsonProperty("shopping-cart")Product[] products;

    /**
     * Create a new shopping cart
     * 
     * @param id the user's id
     * @param productArr the array of product in the user's shopping cart's
     */
    public ShoppingCart(@JsonProperty("id")int id, @JsonProperty("shopping-cart")Product[] productArr) {
        this.id = id;
        this.products = productArr;
    }
    
    /**
     * {@inheritDoc}
     */
    public int getId() {
        return id;
    }

    /**
     * Finds a product from the list of products in the cart
     * 
     * @param product the product to compare to
     * @return the index of the product if it is in the cart, -1 if not in the cart
     */
    private int findProduct(Product product){
        for (int i = 0; i < products.length; i++){
            if (product.getId() == products[i].getId()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Add the product to the cart and increase the array's amount
     * 
     * @param product the product to add to the array
     */
    private void add(Product product) {
        products = Arrays.copyOf(products, products.length + 1);
        products[products.length - 1] = product;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProduct(Product product) throws IOException {
        int i = findProduct(product);

        if (i != -1){
            product = products[i];
            product.setQuantity(product.getQuantity() + 1);
        }
        else{
            product.setQuantity(1);
            add(product);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProduct(Product product) {
        ArrayList<Product> arr = new ArrayList<>();
        Collections.addAll(arr, products);
        arr.remove(product);
        products = new Product[arr.size()];
        arr.toArray(products);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product editProductQuantity(Product product, int amount) throws IOException {
        int i = findProduct(product);

        // if the quantity will be 0
        if (amount <= 0){
            // remove the product
            removeProduct(product);
        }
        else if (i != -1) {
            product = products[i];
            product.setQuantity(amount);
        }
        else{
            product.setQuantity(0);
        }

        return product;
    }

    /**
     * Checks if the shopping cart is empty
     * 
     * @return true if it is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return 0 == products.length;
    }

    /**
     * Empties the shopping cart
     */
    @Override
    public void clearCart() {
        products = new Product[0];
    }

    /**
     * Buy everything currently in the cart
     * 
     * @return the total cost
     * @throws IOException
     */
    public double buyEntireCart() throws IOException {
        double total = 0;
        Product product;

        Iterator<Product> iterateProducts = Arrays.asList(products).iterator();
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
    public Product[] getProducts() {
        return products;
    }
    
    /**
     * Gets the total cost of all products currently in the shopping cart
     * 
     * @return the total cost
     * @throws IOException
     * @author Alex Vernes
     */
    public double getTotalCost() throws IOException {
        double total = 0; 
        Iterator<Product> iterateProducts = Arrays.asList(products).iterator();
        Product product;

        while (iterateProducts.hasNext()){
            product = iterateProducts.next();
            
            // sum up the purchase
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
}
