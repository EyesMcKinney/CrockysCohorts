package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests ShoppingCartFileDAO
 * 
 * @author Holden Lalumiere
 */
@Tag("Persistence-tire")
public class ShoppingCartFileDAOTest {
    
    private static final Object[] Product = null;

    // CuT
    private ShoppingCartFileDAO shoppingCartFileDAO;

    // mock objects
    private ObjectMapper mockObjectMapper;
    //private ShoppingCartDAO mockShoppingCartDAO;

    // test objects
    private String file = "randomFile.txt";
    private ShoppingCart[] testShoppingCarts = new ShoppingCart[2];
    private Product[] p1 = new Product[0];
    private Product[] p2 = new Product[0];
    private int id1 = 3;
    private int id2 = 4;

    private final Product TEST_PRODUCT = new Product(5, "shoe", 6.77, 10, "This product is a shoe");
    
    @BeforeEach
    void setup() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        
        testShoppingCarts[0] = new ShoppingCart(id1, p1);
        testShoppingCarts[1] = new ShoppingCart(id2, p2);
        
        when(mockObjectMapper.readValue(new File ("randomFile.txt"), ShoppingCart[].class)).thenReturn(testShoppingCarts);
        shoppingCartFileDAO = new ShoppingCartFileDAO(file, mockObjectMapper);
    }

    /**
     * Tests if get cart returns the correct cart
     */
    @Test
    void testGetCart() throws IOException{
        // Invoke
        ShoppingCart sc = shoppingCartFileDAO.getCart(id1);

        // Check
        assertEquals(testShoppingCarts[0].getId(), sc.getId());
    }

    /**
     * Test if add product correctly adds a product
     */
    @Test
    void testAddProduct() throws IOException{
        // Invoke
        shoppingCartFileDAO.addProduct(id1, TEST_PRODUCT);

        // Check
        assertEquals(TEST_PRODUCT, testShoppingCarts[0].getProducts()[0]);
    }




}
