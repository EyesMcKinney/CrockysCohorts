package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Controller-tier")
public class ShoppingCartTest {
    
    // CuT
    private ShoppingCart shoppingCart;    

    // Test objects
    private final Product TEST_PRODUCT = new Product(5, "shoe", 6.77, 10, "This product is a shoe");

    /**
     * Make a new shoping cart before each test
     */
    @BeforeEach
    void setup(){
        shoppingCart = new ShoppingCart();
    }

    /**
     * Test if add product adds a product to the array
     */
    @Test
	void testAddProduct() throws IOException {
        // Invoke
        shoppingCart.addProduct(TEST_PRODUCT);

        // Check
        Product addedProduct = shoppingCart.getProducts()[0];
        assertEquals(TEST_PRODUCT, addedProduct);
        assertEquals(TEST_PRODUCT.getQuantity(), addedProduct.getQuantity());
    }
    //TODO add more tests for addproduct

    /**
     * Test if remove product removes a product from the array
     */
    @Test
    void testRemoveProduct() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);

        // Invoke
        shoppingCart.removeProduct(TEST_PRODUCT);
        
        // Check
        Product[] ps = new Product[shoppingCart.getProducts().length];
        assertArrayEquals(ps, shoppingCart.getProducts());
    }


    /**
     * Test if is empty returns false when shopping cart has a product
     */
    @Test
    void testIsEmptyFalse() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);

        // Invoke
        boolean empty = shoppingCart.isEmpty();
        
        // Check
        assertFalse(empty);
    }

    /**
     * Test if is empty returns true when shopping cart has no products
     */
    @Test
    void testIsEmptyTrue() throws IOException{
        // Invoke
        boolean empty = shoppingCart.isEmpty();
        
        // Check
        assertTrue(empty);
    }

    /**
     * Tests if clear cart removes all products from the cart
     */
    @Test
    void clearCart() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);
        // Invoke
        shoppingCart.clearCart();
        
        // Check
        assertTrue(shoppingCart.isEmpty());
    }



}
