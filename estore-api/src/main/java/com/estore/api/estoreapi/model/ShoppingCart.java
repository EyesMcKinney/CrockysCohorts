package com.estore.api.estoreapi.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart {

    @JsonProperty("shopping cart") private List<Product> products;

    public ShoppingCart(){
        this.products = new LinkedList<>();
    }

}
