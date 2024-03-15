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
    public int createProduct(Product product) {
        productRepository.save(product);
        return 0;
    }

    @SuppressWarnings("null")
    @Override
    public int updateProduct(Product product) {
        productRepository.save(product);
        return 0;
    }

    @Override
    public int deleteProduct(int Id) {
        productRepository.deleteById(Id);
        return 0;
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
