package com.example.RegisterLogin.Service.IMPL;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.RegisterLogin.Entity.Product;
import com.example.RegisterLogin.Repo.ProductRepository;
import com.example.RegisterLogin.Service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressWarnings("null")
    @Override
    public String createProduct(Product product) {
        productRepository.save(product);
        return "Product create success";
    }

    @SuppressWarnings("null")
    @Override
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "Product update success";
    }

    @Override
    public String deleteProduct(int Id) {
        productRepository.deleteById(Id);
        return "Product delete success";
    }

    @Override
    public Product geProduct(int Id) {
        return productRepository.findById(Id).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

}
