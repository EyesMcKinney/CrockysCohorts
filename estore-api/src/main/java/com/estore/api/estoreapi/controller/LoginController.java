package com.estore.api.estoreapi.controller;

import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.UserDAO;
import com.estore.api.estoreapi.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 
     * @param username
     */
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return null;
    }

    public ResponseEntity<User> createUser(@RequestBody User user)
}
