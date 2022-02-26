package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.User;

public interface UserDAO {
    
    User createUser(String text) throws IOException;

    User[] findUsers(String text) throws IOException;

    User[] getUsers() throws IOException;

    ShoppingCart getCart(User user) throws IOException;
}
