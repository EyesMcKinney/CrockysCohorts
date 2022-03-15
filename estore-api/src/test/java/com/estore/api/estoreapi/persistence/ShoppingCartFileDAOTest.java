package com.estore.api.estoreapi.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

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
    private Product[] p1;
    private Product[] p2;
    
    @BeforeEach
    void setup() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        
        testShoppingCarts[0] = new ShoppingCart(3, p1);
        testShoppingCarts[1] = new ShoppingCart(4, p2);
        
        when(mockObjectMapper.readValue(new File ("randomFile.txt"), ShoppingCart[].class)).thenReturn(testShoppingCarts);
        shoppingCartFileDAO = new ShoppingCartFileDAO(file, mockObjectMapper);
    }

    /**
     * 
     */






}
