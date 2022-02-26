package com.estore.api.estoreapi.controller;

import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.UserDAO;
import com.estore.api.estoreapi.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Handles the REST API requests for the {@linkplain User} resource.
 * 
 * The Spring annotation identifies this module as a REST API method handler.
 * 
 * @author Stevie Alvarez
 */
@RestController
@RequestMapping("user_login")
public class LoginController {
    /**
     * Log messages in the server via standard IO
     */
    private static final Logger LOG = Logger.getLogger(InventoryController.class.getName());
    private UserDAO userDAO;


    /**
     * 
     * @param inventoryDAO The {@link UserDAO Product Data Access Object} to perform CRUD operations
     * This dependency is injected by the Spring Framework
     */
    public LoginController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    /**
     * Responds to the GET request for a {@linkplain User User} for the given username
     * 
     * @param username The username used to locate the {@link User User}
     * 
     * @return ResponseEntity with {@link User User} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return null;
    }


    /**
     * Creates a {@linkplain User User} with the provided product object
     * 
     * @param product - The {@link User User} to create
     * 
     * @return ResponseEntity with created {@link User User} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link User User} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }
}
