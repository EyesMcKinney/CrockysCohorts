package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserFileDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for all public methods in {@linkplain LoginController}
 * 
 * @author Alex Vernes
 */
@Tag("Controller-tier")
public class LoginControllerTest {
    // CuT
    private LoginController loginController;

    // Mock object
    private UserFileDAO mockUserFileDAO;

    // Test objects
    private final User TEST_USER = new User(1, "username");

    @BeforeEach
    public void setup() {
        mockUserFileDAO = mock(UserFileDAO.class);
        loginController = new LoginController(mockUserFileDAO);
    }

    /**
     * Test {@linkplain LoginController}'s getUser() method for existing users. 
     * @throws IOException
     */
    @Test
    public void testGetUser() throws IOException{
        when(mockUserFileDAO.getUser(TEST_USER.getUsername())).thenReturn(TEST_USER);
        
        // Invoke
        ResponseEntity<User> response = loginController.getUser(TEST_USER.getUsername());

        // Check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_USER, response.getBody());
    }

    /**
     * Test {@linkplain LoginController}'s getUser() method for non-existing users. 
     * @throws IOException
     */
    @Test
    public void testGetUserFailed() throws IOException{
        when(mockUserFileDAO.getUser(TEST_USER.getUsername())).thenReturn(null);
        
        // Invoke
        ResponseEntity<User> response = loginController.getUser(TEST_USER.getUsername());

        // Check
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Test {@linkplain LoginController}'s getUser() method for IOExceptions. 
     * @throws IOException
     */
    @Test
    public void testGetUserError() throws IOException{
        doThrow(new IOException()).when(mockUserFileDAO).getUser(TEST_USER.getUsername());

        // Invoke
        ResponseEntity<User> response = loginController.getUser(TEST_USER.getUsername());

        // Check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test {@linkplain LoginController}'s createUser() method for user creation. 
     * @throws IOException
     */
    @Test
    public void testCreateUser() throws IOException{
        when(mockUserFileDAO.createUser(TEST_USER)).thenReturn(TEST_USER);

        // Invoke
        ResponseEntity<User> response = loginController.createUser(TEST_USER);

        // Check
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_USER, response.getBody());
    }

    /**
     * Test {@linkplain LoginController}'s createUser() method for user creation failiure. 
     * @throws IOException
     */
    @Test
    public void testCreateUserFailed() throws IOException{
        when(mockUserFileDAO.createUser(TEST_USER)).thenReturn(null);
        
        // Invoke
        ResponseEntity<User> response = loginController.createUser(TEST_USER);

        // Check
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(TEST_USER, response.getBody());
    }

    /**
     * Test {@linkplain LoginController}'s createUser() method for IOExceptions. 
     * @throws IOException
     */
    @Test
    public void testCreateUserError() throws IOException{
        doThrow(new IOException()).when(mockUserFileDAO).createUser(TEST_USER);

        // Invoke
        ResponseEntity<User> response = loginController.createUser(TEST_USER);

        // Check
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


}
