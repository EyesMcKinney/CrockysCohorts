package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.estore.api.estoreapi.persistence.InventoryFileDAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class InventoryControllerTest {
    // CuT (Conponent under Test)
    private InventoryController inventoryController;

    // Mock objects
    private InventoryDAO mockInventoryFileDAO;

    // Test objects
    private final Product TEST_PRODUCT = new Product(11, "Crocy's Croc", 10.99, 2, "Crocy's Croc Description");


    /// run before each test
    @BeforeEach
    public void setup() {
        mockInventoryFileDAO = mock(InventoryFileDAO.class);
        inventoryController = new InventoryController(mockInventoryFileDAO);
    }

    /// run after each test
    @AfterEach
    public void cleanup() {

    }


    @Test
    public void testGetProduct() throws IOException {
        // setup
        when(mockInventoryFileDAO.getProduct(TEST_PRODUCT.getId())).thenReturn(TEST_PRODUCT);

        // invoke
        ResponseEntity<Product> response = inventoryController.getProduct(TEST_PRODUCT.getId());

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT, response.getBody());
    }
}
