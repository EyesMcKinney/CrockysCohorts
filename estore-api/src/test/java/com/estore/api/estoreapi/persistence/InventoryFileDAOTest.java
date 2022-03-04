package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
/**
 * InventoryFileDAOTest.java
 * A file made for testing inventory file DAO
 * @author: Isaac S McKinney
 * 3/2/2022
 */
@Tag("Persistence-tier")
public class InventoryFileDAOTest {
    //CuT
    private InventoryFileDAO mockFileDAO;

    //Mock
    private ObjectMapper mockObjectMapper;

    //Mock product list
    private Product[] testProducts;

    // Set up the test equipment for the test functions to use
    @BeforeEach
    public void setup() throws StreamReadException, DatabindException, IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testProducts = new Product[3];
        testProducts[0] = new Product(0, "Sweater", 12, 1, "A cozy sweater");
        testProducts[1] = new Product(1, "Hat", 69, 12, "A cozy hat");
        testProducts[2] = new Product(2, "Daniel Bliss Plush", 420, 0, "Best TA");

        when(mockObjectMapper.readValue(new File("partyInTheUSA.txt"), Product[].class)).thenReturn(testProducts);
        mockFileDAO = new InventoryFileDAO("partyInTheUSA.txt", mockObjectMapper);
    }

    // Test if InventoryFileDAO's getInventory works
    // Get all the products from the set up and compare 
    @Test
    public void testGetInventory() throws IOException{
        Product[] products = mockFileDAO.getInventory();

        assertEquals(testProducts.length, products.length);
        for (int x = 0; x < testProducts.length; x++){
            assertEquals(testProducts[x].getId(), products[x].getId());
            assertEquals(testProducts[x].getName(), products[x].getName());
        }
    }

    // Test if InventroyFileDAO's getProduct works
    // Get a single product and check if it matches the first test product we made
    @Test 
    public void testGetProduct() throws IOException{
        Product result = mockFileDAO.getProduct(0);
        
        assertEquals(testProducts[0], result);
    }

    // Test if InventoryFileDAO's createProduct works
    // See if we can create the product into our test products list and find it again
    @Test
    public void testCreate() throws IOException{
        Product product = new Product(3, "Crochet Quagsire", 999, 999, "Best Boy");
        Product result = assertDoesNotThrow(() -> mockFileDAO.createProduct(product), "Unhandled!");

        assertNotNull(result);
        Product actual = mockFileDAO.getProduct(3);
        assertEquals(result.getId(), actual.getId());
        assertEquals(result.getName(), actual.getName());
    }

    // Test if InventoryFileDAO's updateProduct works
    // See if we can create a product and then update another product with it
    @Test
    public void testUpdate() throws IOException{
         Product product = new Product(0, "Scarf", 25, 50, "A cozy scarf");
         Product result = assertDoesNotThrow(() -> mockFileDAO.updateProduct(product), "Unhandled!");
        
         assertNotNull(result);
         Product actual = mockFileDAO.getProduct(product.getId());
         assertEquals(actual, product);
    }

    // Test if InventoryFileDAO's updateProduct fails or not
    // see if the code returns the correct error response when given invalid items
    @Test
    public void testUpdateFail() throws IOException{
        Product product = new Product(99, "Scarf", 25, 50, "A cozy scarf");
        Product result = assertDoesNotThrow(() -> mockFileDAO.updateProduct(product), "Unhandled!");
        
        assertNull(result);
    }

    // Test if Save correctly throws an exception
    // Save will throw an IOException since no file is found
    @Test
    public void testSaveException() throws StreamWriteException, DatabindException, IOException{
        doThrow(new IOException()).when(mockObjectMapper).writeValue(any(File.class), any(Product[].class));
        Product product = new Product(99, "Scarf", 25, 50, "A cozy scarf");

        assertThrows(IOException.class, () -> mockFileDAO.createProduct(product), "Unhandled!");
    }

    // Test if findProducts is working. 
    // retruns one product
    @Test
    public void testFind() throws IOException{
        Product[] result = assertDoesNotThrow(() -> mockFileDAO.findProducts("Plush"), "Unhandled!");

        assertNotNull(result);
        Product[] actual = new Product[1];
        actual[0] = mockFileDAO.getProduct(2);
        assertEquals(result[0], actual[0]); 
    }

    // Test if findProducts is working. 
    // retruns multiple products
    @Test 
    public void testFindMultiple() throws IOException{
        Product[] result = assertDoesNotThrow(() -> mockFileDAO.findProducts("cozy"), "Unhandled!");
        
        assertNotNull(result);
        Product[] actual = new Product[2];
        actual[0] = mockFileDAO.getProduct(0);
        actual[1] = mockFileDAO.getProduct(1);
        assertEquals(result[0], actual[0]); 
        assertEquals(result[1], actual[1]);
    }

    // Test if deleteProduct works
    // We select a product to delete and *poof* its gone.
    @Test
    public void testDelete() throws IOException{
        Boolean result = assertDoesNotThrow(() -> mockFileDAO.deleteProduct(testProducts[2].getId()), "Unhandled!");
        assertNotNull(result);
        Boolean actual = true;
        assertEquals(actual, result);
    }

    // Test if deleteProduct fails properly
    // We can't delete a product for any reason (Ex. It doesn't exsist)
    // a correct error is thrown
    @Test
    public void testDeleteFail() throws IOException{
        Boolean result = assertDoesNotThrow(() -> mockFileDAO.deleteProduct(99), "Unhandled!");
        assertNotNull(result);
        Boolean actual = false;
        assertEquals(actual, result);
    }
}
