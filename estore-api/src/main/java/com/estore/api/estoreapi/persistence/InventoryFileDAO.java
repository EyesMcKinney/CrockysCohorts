package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implements the functionality for JSON file-based peristance for Heroes
 * 
 * {@literal Component} Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Holden Lalumiere, Tylin Hartman, Isaac McKinney, Alex Vernes, Stevie Alvarez
 */
@Component
public class InventoryFileDAO implements InventoryDAO {
    private static final Logger LOG = Logger.getLogger(InventoryFileDAO.class.getName());
    
    /**
     * Local cache of {@link Product} objects
     */
    Map<Integer, Product> products;

    /**
     * Converts between {@link Product} java objects and JSON text
     */
    private ObjectMapper oMapper;

    /**
     * Next ID value to assign to a product
     */
    private static int currId;

    /**
     * Name of file to read from and write to
     */
    private String filename;

    public InventoryFileDAO(@Value("${products.file}") String filename, ObjectMapper oMapper) throws IOException {
        this.filename = filename;
        this.oMapper = oMapper;
        load();
    }

    /**
     * Generate the next id for a new {@linkplain Product product}
     * 
     * @return The new id
     */
    private synchronized static int nextId() {
        int id = currId;
        ++currId;
        return id;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @author Tylin Hartman
     */
    public Product createProduct(Product product) throws IOException {
        synchronized(products) {
            // We create a new hero object because the id field is immutable
            // and we need to assign the next unique id
            Product newProduct = new Product(nextId(),product.getName());
            products.put(newProduct.getId(),newProduct);
            save(); // may throw an IOException
            return newProduct;
        }
    }

    
    /**
     * Generates and array of Products from the tree map for any 
     * Product that contains the text specified by containsText. 
     * 
     * If containsText is null, the array contains all of the 
     * Product in the tree map. 
     * 
     * @return the array of Products, may be empty
     * 
     * @author Alex Vernes
     */
    public Product[] searchForProduct(String text) throws IOException {
        // TODO Auto-generated method stub
        // TODO Search for the text in both the name and the description
        synchronized(products) {
            ArrayList<Product> productArrayList = new ArrayList<>();

            for (Product product : products.values()) {
                if (text == null || product.getName().contains(text) || product.getDescription().contains(text)) {
                    productArrayList.add(product);
                }
            }
            Product[] productArray = new Product[productArrayList.size()];
            productArrayList.toArray(productArray);
            return productArray;
        }
    }

    /**
     ** {@inheritDoc}
     * @author Holden Lalumiere
     */
    public Product updateProduct(Product product) throws IOException {
        synchronized(products) {
            if (products.containsKey(product.getId()) == false){
                return null;  // product does not exist
            }
            products.put(product.getId(), product);
            save(); // may throw an IOException
            return product;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @author Stevie Alvarez
     */
    @Override
    public Product getProduct(int id) throws IOException {
        synchronized(products) {
            return products.get(id);  // Recall: Map.get(s) returns null if s not present
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @author Isaac S McKinney
     */
    public Product[] getInventory() throws IOException {
        synchronized(products) {
            return searchForProduct(null);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @author Stevie Alvarez
     */
    public boolean deleteProduct(int id) throws IOException {
        synchronized(products) {
            if (products.containsKey(id)) {
                products.remove(id);
                return save();
            
            } else {
                return false;
            }

        }
    }

    /**
     * Saves the {@linkplain Hero heroes} from the map into the file as an array of JSON objects
     * 
     * @author Isaac S McKinney
     * @return true if the {@link Hero heroes} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Product[] ProductArray = searchForProduct(null);

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        oMapper.writeValue(new File(filename), ProductArray);
        return true;
    }

    /**
     * Loads {@linkplain Hero heroes} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * @author Isaac S McKinney
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException{
        products = new TreeMap<>();
        currId = 0;

        // Deserializes the JSON objects from the file into an array of heroes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Product[] ProductArray = oMapper.readValue(new File(filename),Product[].class);

        // Add each hero to the tree map and keep track of the greatest id
        for (Product product : ProductArray) {
            products.put(product.getId(), product);
            if (product.getId() > currId)
                currId = product.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++currId;
        return true;
    }
}
