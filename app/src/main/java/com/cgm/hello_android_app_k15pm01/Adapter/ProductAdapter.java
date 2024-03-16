package com.cgm.hello_android_app_k15pm01.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cgm.hello_android_app_k15pm01.R;
import com.cgm.hello_android_app_k15pm01.entities.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    public List<Product> products;
    private OnItemClickListener onItemClickListener;

    public ProductAdapter(List<Product> products, OnItemClickListener onItemClickListener) {
        this.products = products;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(product));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageView;
        private TextView productNameTextView;
        private TextView productPriceTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
        }

        void bind(Product product) {
            Glide.with(itemView.getContext())
                    .load(product.getImage())
                    .into(productImageView);
            productNameTextView.setText(product.getName());
            productPriceTextView.setText(String.valueOf(product.getPrice()));
        }
    }
}