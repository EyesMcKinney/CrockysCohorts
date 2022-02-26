package com.estore.api.estoreapi.model;

public interface Cart {

    public void addProduct(Product product);

    public void removeProduct(Product product);

    public void editProductQuantity(Product product, int amount);

    public boolean isProductOutOfStock(Product product);

    public boolean isEmpty();


    // public Product[] getProducts();

    // public boolean clearCart();
}
