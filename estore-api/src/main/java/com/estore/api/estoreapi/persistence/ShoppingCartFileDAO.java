package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implements the functionality for JSON file-based peristance for {@linkplain ShoppingCart ShoppingCarts}
 * 
 * @author Stevie Alvarez
 */
@Component
public class ShoppingCartFileDAO implements ShoppingCartDAO {
    private static final Logger LOG = Logger.getLogger(InventoryFileDAO.class.getName());

    /**
     * Local cache of {@link ShoppingCart} objects
     */
    private Map<Integer, ShoppingCart> carts;

    /**
     * Converts between {@link ShoppingCart} java objects and JSON text
     */
    private ObjectMapper oMapper;

    /**
     * Name of file to read from and write to
     */
    private String filename;

    /**
     * Creates a ShoppingCart File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public ShoppingCartFileDAO(@Value("${shoppingcart.file}") String filename, ObjectMapper oMapper) throws IOException {
        this.filename = filename;
        this.oMapper = oMapper;
        load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingCart getCart(int id) throws IOException {
        return user.getCart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCart(int id, Product product) throws IOException {
        user.addToCart(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFromCart(int id, Product product) throws IOException {
        user.removeFromCart(product);
    }

    /**
     * save the users
     * 
     * @throws IOException when file cannot be accessed or read from
     * @throws StreamWriteException
     * @throws DatabindException
     */
    private Boolean save() throws IOException {
        ShoppingCart[] cartArray = searchForCart(null);
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        oMapper.writeValue(new File(filename), cartArray);
        return true;
    }

    /**
     * load the users
     * 
     * @throws IOException when file cannot be accessed or read from
     * @throws StreamWriteException
     * @throws DatabindException
     */
    private boolean load() throws IOException {
        carts = new HashMap<>();
        // Deserializes the JSON objects from the file into an array of users
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        ShoppingCart[] cartArray = oMapper.readValue(new File(filename), ShoppingCart[].class);
        for (ShoppingCart cart : cartArray) {
            carts.put(cart.getId(), cart);
        }
        return true;
    }
    
}
