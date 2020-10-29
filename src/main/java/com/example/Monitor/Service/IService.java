package com.example.Monitor.Service;

import com.example.Monitor.Model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IService {
    public List<Product> getProducts();
    public void getProductsTouchScreen(boolean touchscreen);
    public void getProductsSize(int size);
    public void getProductsCategory(String category);
    public void getProductsTransportMonitor(boolean transportMonitor);
    public Set<Product> getProductSpecs();
    public List<Product> getProductsAccParameters(boolean touchscreen, int size, String category, boolean transportMonitor);
    public Optional<Product> getProductsById(int pid);
    public Product addProduct(Product b);
    public Product updateProduct(Product b);
    public String deleteProductById(int pid);

}
