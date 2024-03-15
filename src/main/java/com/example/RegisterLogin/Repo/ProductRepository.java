package com.example.RegisterLogin.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RegisterLogin.Entity.Product;



public interface ProductRepository extends JpaRepository<Product,Integer>  {

}
