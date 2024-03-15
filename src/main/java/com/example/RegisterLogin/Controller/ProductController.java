package com.example.RegisterLogin.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterLogin.Entity.Product;
import com.example.RegisterLogin.Service.ProductService;




@RestController
@RequestMapping("/api/products")

public class ProductController {
    

   ProductService productService;

   public ProductController (ProductService productService){
     this.productService=productService;
   }
    
    @GetMapping("/{Id}")
    public Product getProductDetail(@PathVariable("Id") int Id){

       return productService.geProduct(Id);
    }

    @GetMapping()
    public List<Product> getAllProductDetail(){
        
        return productService.getAllProduct();
    }
    @PostMapping
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product){
       productService.updateProduct(product);
    }

    @DeleteMapping("{Id}")
    public void deleteProduct(@PathVariable("Id") int Id){
        productService.deleteProduct(Id);
    }
}
