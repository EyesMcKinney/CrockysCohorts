package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        return carts.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProduct(int id, Product product) throws IOException {
        ShoppingCart cart = getCart(id);
        cart.addProduct(product);
        save();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFromCart(int id, Product product) throws IOException {
        ShoppingCart cart = getCart(id);
        cart.removeProduct(product);
        save();
    }

    /**
     * Turn shoppingcart map to an array
     * 
     * @return array of all shopping carts
     */
    private ShoppingCart[] makeArray() {
        synchronized(carts) {
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            for (int id : carts.keySet()) {
                shoppingCarts.add(carts.get(id));
            }
            ShoppingCart[] cartArray = new ShoppingCart[shoppingCarts.size()];
            shoppingCarts.toArray(cartArray);
            return cartArray;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product editProductQuantity(int id, Product product, int amount) throws IOException {
        Product p = carts.get(id).editProductQuantity(product, amount);
        save();
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearCart(int id) throws IOException {
        carts.get(id).clearCart();
        save();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double buyEntireCart(int id) throws IOException {
        Double cost = carts.get(id).buyEntireCart();
        save();
        return cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalCost(int id) throws IOException {
        return carts.get(id).getTotalCost();
    }

    /**
     * save the users
     * 
     * @throws IOException when file cannot be accessed or read from
     * @throws StreamWriteException
     * @throws DatabindException
     */
    private Boolean save() throws IOException {
        ShoppingCart[] cartArray = makeArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        oMapper.writeValue(new File(filename), cartArray);
        return true;
    }

    /**
     * load the {@linkplain ShoppingCart ShoppingCart's}
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
