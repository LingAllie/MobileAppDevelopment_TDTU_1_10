package com.tnl.lab08_ex2;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>
{
    private List<Product> products;
    private OnProductClickListener listener;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    public void setListener(OnProductClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product, parent, false);

        return new ProductHolder(view, products);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product, listener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener {

        private List<Product> products;
        TextView title, price, desc;

        public ProductHolder(@NonNull View itemView, List<Product> products) {
            super(itemView);
            this.products = products;
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            desc = itemView.findViewById(R.id.desc);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(Product product, OnProductClickListener listener) {
            title.setText(product.getName());
            price.setText("$" + String.format("%,d", product.getPrice()));
            desc.setText(product.getDescription());

            if (product.isLoading()) {
                title.setTextColor(ContextCompat.getColor(title.getContext(), R.color.app_name_loading));
                price.setTextColor(ContextCompat.getColor(title.getContext(), R.color.app_price_loading));
                desc.setTextColor(ContextCompat.getColor(title.getContext(), R.color.app_desc_loading));
            }else {
                title.setTextColor(ContextCompat.getColor(title.getContext(), R.color.app_name));
                price.setTextColor(ContextCompat.getColor(title.getContext(), R.color.app_price));
                desc.setTextColor(ContextCompat.getColor(title.getContext(), R.color.app_desc));
            }
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProductClick(getAdapterPosition(), product);
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            int position = getAdapterPosition();
            Product product = products.get(position);

            menu.setHeaderTitle(product.getName());
            menu.add(position, R.id.edit, 0, "Edit");//groupId, itemId, order, title
            menu.add(position, R.id.delete, 0, "Delete");
        }


    }
}
