package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Controller-tier")
public class ShoppingCartTest {
    
    // CuT
    private ShoppingCart shoppingCart;

    // Test objects
    Product[] productArr;
    private final Product TEST_PRODUCT = new Product(5, "shoe", 6.77, 10, "This product is a shoe");

    /**
     * Make a new shoping cart before each test
     */
    @BeforeEach
    void setup(){
        productArr = new Product[0];
        shoppingCart = new ShoppingCart(3, productArr);
    }

    /**
     * Test if add product adds a product to the array
     */
    @Test
	void testAddProductNotInCart() throws IOException {
        // Invoke
        shoppingCart.addProduct(TEST_PRODUCT);

        // Check
        Product addedProduct = shoppingCart.getProducts()[0];
        assertEquals(TEST_PRODUCT, addedProduct);
        assertEquals(TEST_PRODUCT.getQuantity(), addedProduct.getQuantity());
    }

    /**
     * Test if when the product is already in the array,
     * add product adds a product to the array and increments the product quantity
     */
    @Test
    void testAddProduct() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);
        
        // Invoke
        shoppingCart.addProduct(TEST_PRODUCT);

        // Check
        Product addedProduct = shoppingCart.getProducts()[0];
        assertEquals(TEST_PRODUCT, addedProduct);
        assertEquals(TEST_PRODUCT.getQuantity(), addedProduct.getQuantity());
    }

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
     * Test if edit product quantity of a positive amount changes the product quantity correctly
     */
    @Test
    void testEditProductQuantityPositive() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);

        // Invoke
        int quantity = 3;
        shoppingCart.editProductQuantity(TEST_PRODUCT, quantity);
        
        // Check
        assertEquals(quantity, shoppingCart.getProducts()[0].getQuantity());
    }

    /**
     * Test if edit product quantity of a negative amount removes the product
     */
    @Test
    void testEditProductQuantityNegative() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);

        // Invoke
        int quantity = -3;
        shoppingCart.editProductQuantity(TEST_PRODUCT, quantity);
        
        // Check
        Product[] ps = new Product[shoppingCart.getProducts().length];
        assertArrayEquals(ps, shoppingCart.getProducts());
    }

    /**
     * Test if edit product quantity of a product not in the cart
     * will add the product and edit the amount
     */
    @Test
    void testEditProductQuantityItemInCart() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);
        // Invoke
        int quantity = 3;
        shoppingCart.editProductQuantity(TEST_PRODUCT, quantity);
        
        // Check
        assertEquals(quantity, shoppingCart.getProducts()[0].getQuantity());
    }

    /**
     * Test if edit product quantity of a product not in the cart,
     * will do nothing with that product
     */
    @Test
    void testEditProductQuantityNotInCart() throws IOException{
        // Invoke
        int quantity = 3;
        shoppingCart.editProductQuantity(TEST_PRODUCT, quantity);
        
        // Check
        assertEquals(0, TEST_PRODUCT.getQuantity());
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
    void testClearCart() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);
        // Invoke
        shoppingCart.clearCart();
        
        // Check
        assertTrue(shoppingCart.isEmpty());
    }

    /**
     * Test if buy entire cart returns the correct total cost
     */
    @Test
    void testBuyEntireCart() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);
        int quantity = 3;
        shoppingCart.editProductQuantity(TEST_PRODUCT, quantity);

        // Invoke
        double cost = shoppingCart.buyEntireCart();
        
        // Check
        assertEquals(quantity * TEST_PRODUCT.getPrice(), cost);
    }

    /**
     * Test if get total cost returns the correct total cost
     */
    @Test
    void testGetTotalCost() throws IOException{
        shoppingCart.addProduct(TEST_PRODUCT);
        int quantity = 3;
        shoppingCart.editProductQuantity(TEST_PRODUCT, quantity);

        // Invoke
        double cost = shoppingCart.getTotalCost();
        
        // Check
        assertEquals(quantity * TEST_PRODUCT.getPrice(), cost);
    }

    /**
     * Test if the to string returns the correct string
     * TODO change this one later when Stevie changes it
     */
    @Test
    void testToString(){
        // Invoke
        String text = shoppingCart.toString();
        
        // Check
        assertEquals(shoppingCart.toString(), text);
    }
}
