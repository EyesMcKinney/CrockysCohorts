package com.estore.api.estoreapi.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart implements Cart{

    @JsonProperty("shopping cart") private List<Product> products;

    public ShoppingCart(){
        this.products = new LinkedList<>();
    }

    @Override
    public boolean addProduct(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeProduct(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Product[] getProducts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean clearCart() {
        // TODO Auto-generated method stub
        return false;
    }

}
