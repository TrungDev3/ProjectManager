package com.example.RegisterLogin.Service;
import java.util.List;

import com.example.RegisterLogin.Entity.Product;



public interface ProductService {
    
    public String createProduct(Product product);
    public String updateProduct(Product product);
    public String deleteProduct(int Id);
    public Product geProduct(int Id);
    public List<Product> getAllProduct();
}
