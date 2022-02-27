package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Isaac S McKinney
 * @author Stevie Alvarez
 */

@Component
public class UserFileDAO implements UserDAO{
    private static final Logger LOG = Logger.getLogger(InventoryFileDAO.class.getName());
    
    /**
     * Local cache of {@link User} objects
     */
    private ArrayList<User> userList;

    /**
     * Converts between {@link User} java objects and JSON text
     */
    private ObjectMapper oMapper;

    /**
     * Name of file to read from and write to
     */
    private String filename;

    /**
     * Creates a User File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper oMapper) throws IOException {
        this.filename = filename;
        this.oMapper = oMapper;
        load();
    }

    /**
     * {@inheritDoc}
     * 
     * @author Stevie Alvarez
     */
    @Override
    public User getUser(String username) throws IOException {
        synchronized(userList) {
            // return users.get(username);
            // TODO: Change userList from a list to a map, update methods accordingly
            // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(User user) throws IOException {
        synchronized(userList){
            User newUser = new User(user.getName(), user.getCart());
            userList.add(newUser);
            save(); // may throw an IOException
            return newUser;
        }
    }

    /**
     * find a user
     * 
     * @param text - text is user to search for
     * 
     * @return array of similar users
     */
    private User[] searchForUser(String text) {
        synchronized(userList) {
            ArrayList<User> UserArrayList = new ArrayList<>();
            for (User user : userList) {
                if (text == null || user.getName().contains(text)) {
                    UserArrayList.add(user);
                }
            }
            User[] UserArray = new User[UserArrayList.size()];
            UserArrayList.toArray(UserArray);
            return UserArray;
        }
    }

    /**
     * 
     * find users
     * 
     * @param text : string text is the username for the user to be found
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    @Override
    public User[] findUsers(String text) throws IOException {
        synchronized(userList) {
            return searchForUser(text);
        }
    }

    /**
     * get all the users (currently)
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    @Override
    public User[] getUsers() throws IOException {
        synchronized(userList) {
            return searchForUser(null);
        }
    }

    /**
     * get the cart of a certain user
     * 
     * @param user : User to get the cart for
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    @Override
    public ShoppingCart getCart(User user) throws IOException {
        return user.getCart();
    }
    
    /**
     * save the users
     * 
     * @throws IOException when file cannot be accessed or read from
     * @throws StreamWriteException
     * @throws DatabindException
     */
    private Boolean save() throws StreamWriteException, DatabindException, IOException {
        User[] userArray = searchForUser(null);
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        oMapper.writeValue(new File(filename), userArray);
        return true;
    }

    /**
     * load the users
     * 
     * @throws IOException when file cannot be accessed or read from
     * @throws StreamWriteException
     * @throws DatabindException
     */
    private boolean load() throws StreamReadException, DatabindException, IOException {
        ArrayList<User> users = new ArrayList<User>();
        // Deserializes the JSON objects from the file into an array of users
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        User[] UserArray = oMapper.readValue(new File(filename), User[].class);
        for (User user : UserArray) {
            users.add(user);
        }
        return true;
    }
}
