package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserDAO;

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
    private UserDAO mockUserDAO;
    private User user;

    // test objects
    private final Product TEST_PRODUCT = new Product(5, "shoe", 6.77, 10, "This product is a shoe");

    /**
     * Before every test, make a mock shopping cart and a shopping cart controller
     */
    @BeforeEach
    void setup() throws IOException{
        mockShoppingCart = mock(ShoppingCart.class);
        mockUserDAO = mock(UserDAO.class);
        user = new User(1, "frank");
        shoppingCartController = new ShoppingCartController(mockUserDAO);

        when(mockUserDAO.getUser(user.getName())).thenReturn(user);
        when(mockUserDAO.getCart(user)).thenReturn(mockShoppingCart);
    }

    /**
     * Test if get products returns OK and the correct Product's array
     */
    @Test
	void testGetProducts() {
        Product[] products = new Product[1];
        products[0] = TEST_PRODUCT;
        when(mockShoppingCart.getProducts()).thenReturn(products);

        // invoke
        ResponseEntity<Product[]> response = shoppingCartController.getProducts(user.getName());
    
        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
	}

    /**
     * Test when get products gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testGetProductsError() throws IOException {
        doThrow(new IOException()).when(mockUserDAO).getUser(user.getName());

        // invoke
        ResponseEntity<Product[]> response = shoppingCartController.getProducts(user.getName());
    
        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if add product returns CREATED and the product
     */
    @Test
    void testAddProduct(){
        // invoke
        ResponseEntity<Product> response = shoppingCartController.addProduct(user.getName(), TEST_PRODUCT);
    
        // check
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(TEST_PRODUCT, response.getBody());
    }

    /**
     * Test when add product gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testAddProductError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).addProduct(TEST_PRODUCT);

        // invoke
        ResponseEntity<Product> response = shoppingCartController.addProduct(user.getName(), TEST_PRODUCT);
    
        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if remove product returns OK and the product
     */
    @Test
    void testRemoveProduct(){
        // invoke
        ResponseEntity<Product> response = shoppingCartController.removeProduct(user.getName(), TEST_PRODUCT);

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT, response.getBody());
    }

    /**
     * Test when remove product gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testRemoveProductError() throws IOException {
        doThrow(new IOException()).when(mockUserDAO).getUser(user.getName());

        // invoke
        ResponseEntity<Product> response = shoppingCartController.removeProduct(user.getName(), TEST_PRODUCT);
    
        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if edit product quantity returns OK and the product
     */
    @Test
    void testEditProductQuantity() throws IOException {
        // invoke
        when(mockShoppingCart.editProductQuantity(TEST_PRODUCT, TEST_PRODUCT.getQuantity() - 1)).thenReturn(TEST_PRODUCT);
        ResponseEntity<Product> response = shoppingCartController.editProductQuantity(user.getName(), TEST_PRODUCT, TEST_PRODUCT.getQuantity() - 1);

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT, response.getBody());
    }

    /**
     * Test when edit product quantity gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testEditProductQuantityError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).editProductQuantity(TEST_PRODUCT, TEST_PRODUCT.getQuantity() - 1);

        // invoke
        ResponseEntity<Product> response = shoppingCartController.editProductQuantity(user.getName(), TEST_PRODUCT, TEST_PRODUCT.getQuantity() - 1);

        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if buy entire cart returns OK and the total cost of everything in the cart
     */
    @Test
    void testBuyEntireCart() throws IOException{
        // simulates having 1 TEST_PRODUCT in the shopping cart
        when(mockShoppingCart.buyEntireCart()).thenReturn(TEST_PRODUCT.getPrice());


        // invoke
        ResponseEntity<Double> response = shoppingCartController.buyEntireCart(user.getName());

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT.getPrice(), response.getBody());

    }

    /**
     * Test when buy entire cart gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testBuyEntireCartError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).buyEntireCart();

        // invoke
        ResponseEntity<Double> response = shoppingCartController.buyEntireCart(user.getName());

        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test if get total cost returns OK and the total cost of everything in the cart
     */
    @Test
    void testGetTotalCost() throws IOException{
        // simulates having 1 TEST_PRODUCT in the shopping cart
        when(mockShoppingCart.getTotalCost()).thenReturn(TEST_PRODUCT.getPrice());

        // invoke
        ResponseEntity<Double> response = shoppingCartController.getTotalCost(user.getName());

        // check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PRODUCT.getPrice(), response.getBody());
    }

    /**
     * Test when get total cost gets an error, see if it returns INTERNAL_SERVER_ERROR
     */
    @Test
    void testGetTotalCostError() throws IOException{
        doThrow(new IOException()).when(mockShoppingCart).getTotalCost();

        // invoke
        ResponseEntity<Double> response = shoppingCartController.getTotalCost(user.getName());

        // check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
