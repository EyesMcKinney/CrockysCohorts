package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doThrow;
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


/**
 * Unit tests for all public methods in {@linkplain InventoryController}
 * 
 * @author Stevie Alvarez
 */
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


    /**
     * Test {@link InventoryController}'s getProduct() method for existing product
     * @throws IOException
     */
    @Test
    public void testGetProduct() throws IOException {
        // setup
        when(mockInventoryFileDAO.getProduct(TEST_PRODUCT.getId())).thenReturn(TEST_PRODUCT);

        // invoke
        ResponseEntity<Product> response = inventoryController.getProduct(TEST_PRODUCT.getId());

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT, response.getBody());
    }

    /**
     * Test {@link InventoryController}'s getProduct() method for non-existing product
     * @throws IOException
     */
    @Test
    public void testGetProductFailed() throws IOException {
        // setup
        when(mockInventoryFileDAO.getProduct(TEST_PRODUCT.getId())).thenReturn(null);

        // invoke
        ResponseEntity<Product> response = inventoryController.getProduct(TEST_PRODUCT.getId());

        // analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Test {@link InventoryController}'s getProduct() method for system storage issue 
     * @throws IOException
     */
    @Test
    public void testGetProductIOE() throws IOException {
        // setup
        doThrow(new IOException()).when(mockInventoryFileDAO).getProduct(TEST_PRODUCT.getId());
    
        // invoke
        ResponseEntity<Product> response = inventoryController.getProduct(TEST_PRODUCT.getId());

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s createProduct() method for product creation
     * @throws IOException
     */
    @Test
    public void testCreateProduct() throws IOException {
        // setup
        when(mockInventoryFileDAO.createProduct(TEST_PRODUCT)).thenReturn(TEST_PRODUCT);

        // invoke
        ResponseEntity<Product> response = inventoryController.createProduct(TEST_PRODUCT);

        // analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(TEST_PRODUCT, response.getBody());
    }

    /**
     * Test {@link InventoryController}'s createProduct() method for product creation failure 
     * @throws IOException
     */
    @Test
    public void testCreateProductFailed() throws IOException {
        // setup
        when(mockInventoryFileDAO.createProduct(TEST_PRODUCT)).thenReturn(null);

        // invoke
        ResponseEntity<Product> response = inventoryController.createProduct(TEST_PRODUCT);

        // analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Test {@link InventoryController}'s createProduct() method for system storage issue 
     * @throws IOException
     */
    @Test
    public void testCreateProductIOE() throws IOException {
        // setup
        doThrow(new IOException()).when(mockInventoryFileDAO).createProduct(TEST_PRODUCT);
    
        // invoke
        ResponseEntity<Product> response = inventoryController.createProduct(TEST_PRODUCT);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s searchforProduct() method for product array
     * @throws IOException
     */
    @Test
    public void testSearchForProduct() throws IOException {
        // setup
        when(mockInventoryFileDAO.findProducts(TEST_PRODUCT.getName())).thenReturn(new Product[] {TEST_PRODUCT});

        // invoke
        ResponseEntity<Product[]> response = inventoryController.searchforProduct(TEST_PRODUCT.getName());

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(new Product[] {TEST_PRODUCT}, response.getBody());
    }

    /**
     * Test {@link InventoryController}'s searchforProduct() method for product array
     * @throws IOException
     */
    @Test
    public void testSearchForProductDesc() throws IOException {
        // setup
        when(mockInventoryFileDAO.findProducts(TEST_PRODUCT.getDescription())).thenReturn(new Product[] {TEST_PRODUCT});

        // invoke
        ResponseEntity<Product[]> response = inventoryController.searchforProduct(TEST_PRODUCT.getDescription());

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(new Product[] {TEST_PRODUCT}, response.getBody());
    }

    /**
     * Test {@link InventoryController}'s searchforProduct() method for system storage issue
     * @throws IOException
     */
    @Test
    public void testSearchForProductIOE() throws IOException {
        // setup
        doThrow(new IOException()).when(mockInventoryFileDAO).findProducts(TEST_PRODUCT.getName());
    
        // invoke
        ResponseEntity<Product[]> response = inventoryController.searchforProduct(TEST_PRODUCT.getName());

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s getInventory() method for filled product array
     * @throws IOException
     */
    @Test
    public void testGetInventory() throws IOException {
        // setup
        when(mockInventoryFileDAO.getInventory()).thenReturn(new Product[] {TEST_PRODUCT});

        // invoke
        ResponseEntity<Product[]> response = inventoryController.getInventory();

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(new Product[] {TEST_PRODUCT}, response.getBody());
    }

        /**
     * Test {@link InventoryController}'s getInventory() method for empty product array
     * @throws IOException
     */
    @Test
    public void testGetInventoryEmpty() throws IOException {
        // setup
        when(mockInventoryFileDAO.getInventory()).thenReturn(new Product[] {});

        // invoke
        ResponseEntity<Product[]> response = inventoryController.getInventory();

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(new Product[] {}, response.getBody());
    }

    /**
     * Test {@link InventoryController}'s getInventory() method for system storage issue
     * @throws IOException
     */
    @Test
    public void testGetInventoryIOE() throws IOException {
        // setup
        doThrow(new IOException()).when(mockInventoryFileDAO).getInventory();
    
        // invoke
        ResponseEntity<Product[]> response = inventoryController.getInventory();

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s updateProduct() method for updating existing product
     * @throws IOException
     */
    @Test
    public void testUpdateProduct() throws IOException {
        // setup
        when(mockInventoryFileDAO.updateProduct(TEST_PRODUCT)).thenReturn(TEST_PRODUCT);

        // invoke
        ResponseEntity<Product> response = inventoryController.updateProduct(TEST_PRODUCT);

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT, response.getBody());
    }

    /**
     * Test {@link InventoryController}'s updateProduct() method for updating non-existing product
     * @throws IOException
     */
    @Test
    public void testUpdateProductDNE() throws IOException {
        // setup
        when(mockInventoryFileDAO.updateProduct(TEST_PRODUCT)).thenReturn(null);

        // invoke
        ResponseEntity<Product> response = inventoryController.updateProduct(TEST_PRODUCT);

        // analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s updateProduct() method for system storage issue
     * @throws IOException
     */
    @Test
    public void testUpdateProductIOE() throws IOException {
        // setup
        doThrow(new IOException()).when(mockInventoryFileDAO).updateProduct(TEST_PRODUCT);
    
        // invoke
        ResponseEntity<Product> response = inventoryController.updateProduct(TEST_PRODUCT);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s deleteProduct() method for deleting existing product
     * @throws IOException
     */
    @Test
    public void testDeleteProduct() throws IOException {
        // setup
        when(mockInventoryFileDAO.deleteProduct(TEST_PRODUCT.getId())).thenReturn(true);

        // invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(TEST_PRODUCT.getId());

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s deleteProduct() method for deleting non-existing product
     * @throws IOException
     */
    @Test
    public void testDeleteProductDNE() throws IOException {
        // setup
        when(mockInventoryFileDAO.deleteProduct(TEST_PRODUCT.getId())).thenReturn(false);

        // invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(TEST_PRODUCT.getId());

        // analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test {@link InventoryController}'s deleteProduct() method for deleting existing product
     * @throws IOException
     */
    @Test
    public void testDeleteProductIOE() throws IOException {
        // setup
        doThrow(new IOException()).when(mockInventoryFileDAO).deleteProduct(TEST_PRODUCT.getId());

        // invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(TEST_PRODUCT.getId());

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
