package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserFileDAO implements UserDAO{
    private static final Logger LOG = Logger.getLogger(InventoryFileDAO.class.getName());
    
    /**
     * Local cache of {@link Product} objects
     */
    private ArrayList<User> userList;

    /**
     * Converts between {@link Product} java objects and JSON text
     */
    private ObjectMapper oMapper;

    /**
     * Name of file to read from and write to
     */
    private String filename;

    /**
     * Creates a Product File Data Access Object
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

    private boolean load() throws StreamReadException, DatabindException, IOException {
        ArrayList<User> users = new ArrayList<User>();

        // Deserializes the JSON objects from the file into an array of heoes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        User[] UserArray = oMapper.readValue(new File(filename), User[].class);

        // Add each product to the tree map and keep track of the greatest id
        for (User user : UserArray) {
            users.add(user);
        }
        // Make the next id one greater than the maximum from the file
        return true;
    }

    @Override
    public User createUser(String text) throws IOException {
        synchronized(userList){
            User newUser = new User(text);
            userList.add(newUser);
            save(); // may throw an IOException
            return newUser;
        }
    }

    private Boolean save() throws StreamWriteException, DatabindException, IOException {
        User[] userArray = searchForProduct(null);

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        oMapper.writeValue(new File(filename), userArray);
        return true;
    }

    private User[] searchForProduct(String text) {
        synchronized(userList) {
            ArrayList<User> UserArrayList = new ArrayList<>();

            for (User user : userList) {
                if (text == null || user.getUser().contains(text)) {
                    UserArrayList.add(user);
                }
            }
            User[] UserArray = new User[UserArrayList.size()];
            UserArrayList.toArray(UserArray);
            return UserArray;
        }
    }
    
}
