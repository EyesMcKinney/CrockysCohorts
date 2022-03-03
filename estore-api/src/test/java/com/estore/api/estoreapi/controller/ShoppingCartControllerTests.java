package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Tag("Controller-tier")
public class ShoppingCartControllerTests {

    // CuT
    private ShoppingCartController shoppingCartController;

    // mock object
    private ShoppingCart mockShoppingCart;

    // test objects
    private final Product TEST_PRODUCT = new Product(5, "shoe", 6.77, 10, "This product is a shoe");
    
    /**
     * Before every test, make a mock shopping cart and a shopping cart controller
     */
    @BeforeEach
    void setup(){
        mockShoppingCart = mock(ShoppingCart.class);
        shoppingCartController = new ShoppingCartController(mockShoppingCart);
    }

    /**
     * Test if get products returns OK and the correct hash map
     */
    @Test
	void testGetProducts() {
        HashMap<Integer,Integer> mockHashMap = mock(HashMap.class);
        mockHashMap.put(TEST_PRODUCT.getId(), TEST_PRODUCT.getQuantity() - 1);
        when(mockShoppingCart.getProducts()).thenReturn(mockHashMap);

        // invoke
        ResponseEntity<HashMap<Integer,Integer>> response = shoppingCartController.getProducts();
    
        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockHashMap, response.getBody());
	}

    /**
     * Test if add product returns CREATED and the product's id
     */
    @Test
    void testAddProduct(){
        // invoke
        ResponseEntity<Integer> response = shoppingCartController.addProduct(TEST_PRODUCT.getId());
    
        // check
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(TEST_PRODUCT.getId(), response.getBody());
    }

    /**
     * Test when add product gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testAddProductError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).addProduct(TEST_PRODUCT.getId());

        // invoke
        ResponseEntity<Integer> response = shoppingCartController.addProduct(TEST_PRODUCT.getId());
    
        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if remove product returns OK and the product's id
     */
    @Test
    void testRemoveProduct(){
        // invoke
        ResponseEntity<Integer> response = shoppingCartController.removeProduct(TEST_PRODUCT.getId());

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT.getId(), response.getBody());
    }

    /**
     * Test if edit product quantity returns OK and the product's id
     */
    @Test
    void testEditProductQuantity(){
        // invoke
        ResponseEntity<Integer> response = shoppingCartController.editProductQuantity(TEST_PRODUCT.getId(), TEST_PRODUCT.getQuantity() - 1);

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT.getId(), response.getBody());
    }

    /**
     * Test when edit product quantity gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testEditProductQuantityError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).editProductQuantity(TEST_PRODUCT.getId(), TEST_PRODUCT.getQuantity() - 1);

        // invoke
        ResponseEntity<Integer> response = shoppingCartController.editProductQuantity(TEST_PRODUCT.getId(), TEST_PRODUCT.getQuantity() - 1);

        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if buy entire cart returns OK and the total cost of everything in the cart
     */
    @Test
    void testBuyEntireCart() throws IOException{
        // simulates having 1 TEST_PRODUCT in the shopping cart
        when(mockShoppingCart.buyEntireCart()).thenReturn((int)TEST_PRODUCT.getPrice());


        // invoke
        ResponseEntity<Integer> response = shoppingCartController.buyEntireCart();

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals((int)TEST_PRODUCT.getPrice(), response.getBody());

    }

    /**
     * Test when buy entire cart gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testBuyEntireCartError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).buyEntireCart();

        // invoke
        ResponseEntity<Integer> response = shoppingCartController.buyEntireCart();

        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if get total cost returns OK and the total cost of everything in the cart
     */
    @Test
    void testGetTotalCost() throws IOException{
        // simulates having 1 TEST_PRODUCT in the shopping cart
        when(mockShoppingCart.getTotalCost()).thenReturn((int)TEST_PRODUCT.getPrice());

        // invoke
        ResponseEntity<Integer> response = shoppingCartController.getTotalCost();

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals((int)TEST_PRODUCT.getPrice(), response.getBody());
    }

    /**
     * Test when get total cost gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testGetTotalCostError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).getTotalCost();

        // invoke
        ResponseEntity<Integer> response = shoppingCartController.getTotalCost();

        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
