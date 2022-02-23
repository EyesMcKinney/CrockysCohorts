package com.estore.api.estoreapi.model;

public interface Cart {

    public boolean addProduct(int id);

    public boolean removeProduct(int id);

    public Product[] getProducts();

    public User getUser();

    public boolean clearCart();
}
