package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.User;

public interface UserDAO {
    
    /**
     * CREATE a {@linkplain User user} with the provided username.
     * 
     * @param username The username for the new user
     * @return A new user with the provided username
     * @throws IOException If issue with underlying storage
     */
    User createUser(String username) throws IOException;

    /**
     * Retrieve a {@linkplain User user} with the provided username.
     * 
     * @param username The name identifying the desired {@link User user}
     * @return A {@link User user} with the matching username,
     * null if {@link User user} could not be found
     * @throws IOException If issue with underlying storage
     */
    User getUser(String username) throws IOException;
}
