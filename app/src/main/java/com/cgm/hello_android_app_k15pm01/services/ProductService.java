package com.cgm.hello_android_app_k15pm01.services;

import com.cgm.hello_android_app_k15pm01.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @GET("products")
    Call<List<Product>> getProducts();

    @POST("products")
    Call<Product> addProduct(@Body Product product);

    @PUT("products/{id}")
    Call<Product> updateProduct(@Path("id") long id, @Body Product product);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") long id);
}
