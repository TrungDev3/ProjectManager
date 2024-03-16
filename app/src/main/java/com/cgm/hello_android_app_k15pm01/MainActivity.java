package com.cgm.hello_android_app_k15pm01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cgm.hello_android_app_k15pm01.Adapter.ProductAdapter;
import com.cgm.hello_android_app_k15pm01.entities.Product;
import com.cgm.hello_android_app_k15pm01.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {
    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productRecyclerView = findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchProducts();
    }

    private void fetchProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService productService = retrofit.create(ProductService.class);
        productService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList.clear();
                    productList.addAll(response.body());
                    productAdapter = new ProductAdapter(productList, MainActivity.this);
                    productRecyclerView.setAdapter(productAdapter);
                } else {
                    showErrorMessage("Failed to fetch products");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                showErrorMessage("Error: " + t.getMessage());
            }
        });
    }

    private void showAddEditDialog(Product product, boolean isEditMode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_product, null);

        EditText productNameEditText = dialogView.findViewById(R.id.productNameEditText);
        EditText productPriceEditText = dialogView.findViewById(R.id.productPriceEditText);
        EditText productImageEditText = dialogView.findViewById(R.id.productImageEditText);

        if (isEditMode) {
            productNameEditText.setText(product.getName());
            productPriceEditText.setText(String.valueOf(product.getPrice()));
            productImageEditText.setText(product.getImage());
        }

        builder.setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    String name = productNameEditText.getText().toString().trim();
                    double price = Double.parseDouble(productPriceEditText.getText().toString());
                    String image = productImageEditText.getText().toString().trim();

                    if (isEditMode) {
                        updateProduct(product.getId(), name, price, image);
                    } else {
                        addProduct(name, price, image);
                    }
                })
                .setNegativeButton("Cancel", null);

        builder.create().show();
    }

    private void addProduct(String name, double price, String image) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService productService = retrofit.create(ProductService.class);
        Product newProduct = new Product(0, name, price, image);
        productService.addProduct(newProduct).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful()) {
                    Product addedProduct = response.body();
                    productList.add(addedProduct);
                    productAdapter.notifyDataSetChanged();
                    showSuccessMessage("Product added successfully");
                } else {
                    showErrorMessage("Failed to add product");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                showErrorMessage("Error: " + t.getMessage());
            }
        });
    }

    private void updateProduct(int id, String name, double price, String image) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService productService = retrofit.create(ProductService.class);
        Product updatedProduct = new Product(id, name, price, image);
        productService.updateProduct(id, updatedProduct).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful()) {
                    int index = findProductIndex(id);
                    if (index != -1) {
                        productList.set(index, updatedProduct);
                        productAdapter.notifyItemChanged(index);
                        showSuccessMessage("Product updated successfully");
                    } else {
                        showErrorMessage("Product not found");
                    }
                } else {
                    showErrorMessage("Failed to update product");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                showErrorMessage("Error: " + t.getMessage());
            }
        });
    }

    private int findProductIndex(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void deleteProduct(Product product) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService productService = retrofit.create(ProductService.class);
        productService.deleteProduct(product.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    int index = findProductIndex(product.getId());
                    if (index != -1) {
                        productList.remove(index);
                        productAdapter.notifyItemRemoved(index);
                        showSuccessMessage("Product deleted successfully");
                    } else {
                        showErrorMessage("Product not found");
                    }
                } else {
                    showErrorMessage("Failed to delete product");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                showErrorMessage("Error: " + t.getMessage());
            }
        });
    }

    private void showProductOptions(Product product) {
        String[] options = {"Edit", "Delete", "Add New"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(product.getName())
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            showAddEditDialog(product, true);
                            break;
                        case 1:
                            deleteProduct(product);
                            break;
                        case 2:
                            showAddEditDialog(null, false);
                            break;
                    }
                });
        builder.create().show();
    }

    @Override
    public void onItemClick(Product product) {
        showProductOptions(product);
    }

    private void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}