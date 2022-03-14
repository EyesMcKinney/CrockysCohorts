package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Controller-tier")
public class ProductTest {
    
    // CuT
    private Product product;

    /**
     * Make the product before each test
     */
    @BeforeEach
    void setup(){
        product = new Product(5, "shoe", 6.77, 10, "This product is a shoe");
    }

    /**
     * Test if set name changes the name correctly
     */
    @Test
    void testSetName(){
        // Invoke
        String newName = "croc";
        product.setName(newName);

        // Check
        assertEquals(newName, product.getName());
    }

    /**
     * Test if set price changes the price correctly
     */
    @Test
    void testSetPrice(){
        // Invoke
        double newPrice = 9.99;
        product.setPrice(newPrice);

        // Check
        assertEquals(newPrice, product.getPrice());
    }

    /**
     * Test if set description changes the description correctly
     */
    @Test
    void testSetDescription(){
        // Invoke
        String newDescription = "This product is made of leather";
        product.setDescription(newDescription);

        // Check
        assertEquals(newDescription, product.getDescription());
    }
}
