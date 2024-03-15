package com.example.RegisterLogin.Service;
import java.util.List;

import com.example.RegisterLogin.Entity.Product;



public interface ProductService {
    
    public int createProduct(Product product);
    public int updateProduct(Product product);
    public int deleteProduct(int Id);
    public Product geProduct(int Id);
    public List<Product> getAllProduct();
}
