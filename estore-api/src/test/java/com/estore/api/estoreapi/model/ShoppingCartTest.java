package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class ShoppingCartTest {
    
    // CuT
    private ShoppingCart shoppingCart;    

    // Test objects
    private final Product TEST_PRODUCT = new Product(5, "shoe", 6.77, 10, "This product is a shoe");

    /**
     * TODO
     */
    void setup(){
        shoppingCart = new ShoppingCart();
    }

    /**
     * TODO
     * @throws IOException
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

}
