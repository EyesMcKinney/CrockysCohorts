package com.estore.api.estoreapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a Product 
 * 
 * @author Tylin Hartman, Holden Lalumiere, Isaac McKinney
 *         Alex Vernes, Stevie Alvarez
 */

public class Product {
    private static final Logger LOG = Logger.getLogger(Product.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Product [id=%d, name=%s]" ;


    /// id, name, description, price, stock quantity, photo eventually

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("price") private double price ;
    @JsonProperty("descriptioin") private String description ;
    @JsonProperty("quantity") private int quantity ;


    /**
     * Create a {@linkplain Product} with the given name, id, price, 
     * description, and stock quantity
     * (eventually (an) image(s) will be added)
     * 
     * @param id The product id
     * @param name The product name
     * @param price The price of 1 unit of the product
     * @param description The product description
     * @param quantity The amount of product currently in stock
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */

    public Product(@JsonProperty("id") int id, @JsonProperty("name") String name,
                    @JsonProperty("price") double price, @JsonProperty("quantity") int quantity,
                    @JsonProperty("description") String description) {
        
        this.id = id ;
        this.name = name;
        this.price = price;
        this.quantity = quantity ;
        this.description = description;

    }

    /**
     * Create a {@linkplain Product} with the given name and id. Other fields set to default values.
     * 
     * @param id The product id
     * @param name The product name
     */
    public Product(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this(id, name, 1.0, 1, "");
    }

    /**
     * Retrieves the product id
     * @return the product id
     */
    public int getId() { return id ; }

    /**
     * Set product name
     * @param name The product name
     */
    public void setName(String name) { this.name = name ; }

    /**
     * Retrieves the product name
     * @return the product name
     */
    public String getName() { return name ; }

    /**
     * Set product price
     * @param price The product price
     */
    public void setPrice(double price) { this.price = price ; }

    /**
     * Retrieves product price
     * @return The product price
     */
    public double getPrice() { return price ; }

    /**
     * Set product description
     * @param descritption The product description
     */
    public void setDescription(String description) { this.description = description ; }

    /**
     * Retrieves product description
     * @return Product description
     */
    public String getDescription() { return description ; }

    /**
     * Set the stock quantity of the product
     * @param quantity the stock quantity 
     */
    public void setQuantity(int quantity) { this.quantity = quantity ; }

    /**
     * Retrieves the current quantity of the product 
     * that is in stock
     * @return The stock quantity
     */
    public int getQuantity() { return quantity ; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, name) ;
    }



}
