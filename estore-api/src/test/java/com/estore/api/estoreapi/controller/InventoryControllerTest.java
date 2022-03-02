package com.estore.api.estoreapi.controller;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.estore.api.estoreapi.persistence.InventoryFileDAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

@Tag("Controller-tier")
public class InventoryControllerTest {
    // CuT (Conponent under Test)
    private InventoryController inventoryController;

    // Mock objects
    private InventoryDAO mockInventoryFileDAO;

    // Test objects
    private final Product TEST_PRODUCT = new Product(11, "Crocy's Croc", 10.99, 2, "Crocy's Croc Description")

    /// run before each test
    @BeforeEach
    public void setup() {

    }


    /// run after each test
    @AfterEach
    public void cleanup() {

    }
}
