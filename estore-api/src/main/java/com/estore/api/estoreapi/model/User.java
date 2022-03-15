package com.estore.api.estoreapi.model;

import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Isaac S McKinney
 */
public class User{
    private static final Logger LOG = Logger.getLogger(User.class.getName());

    @JsonProperty("username") private String username;
    @JsonProperty("id") private int id;

    static final String STRING_FORMAT = "User [id=%d, username=%s]" ;

    /**
     * Constructor for the user object
     * @param is - user id to be given to the user
     * @param username - user name to be given to the user
     */
    public User(@JsonProperty("id") int id, @JsonProperty("username") String username){
        this.id = id;
        this.username = username;
    }

    /**
     * get the user name of the user
     * @return the users username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Get the ID of the user
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * for testing purposes, puts the user into a readable format
     */
    @Override
    public String toString(){
        return String.format(STRING_FORMAT, id, username);
    }
}