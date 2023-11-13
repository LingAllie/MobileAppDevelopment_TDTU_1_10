package com.tnl.lab08_ex2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.tnl.lab08_ex2.network.BaseJSON;
import com.tnl.lab08_ex2.network.ProductAPI;
import com.tnl.lab08_ex2.network.RestAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ex2Activity extends AppCompatActivity implements OnProductClickListener {

    private List<Product> products;
    private ProductAdapter adapter;
    private RecyclerView recyclerView;
    private ProductAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        products = new ArrayList<>();
        adapter = new ProductAdapter(products);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);

        api = RestAPI.productAPI();

        loadProducts();
        registerForContextMenu(recyclerView);
    }

    private void loadProducts() {

        api.getProduct().enqueue(new Callback<BaseJSON<List<Product>>>() {
            @Override
            public void onResponse(Call<BaseJSON<List<Product>>> call, Response<BaseJSON<List<Product>>> response) {
                BaseJSON<List<Product>> data = response.body();
                if (data == null) {
                    Toast.makeText(Ex2Activity.this, "Cannot receive data from server !!!", Toast.LENGTH_SHORT).show();
                } else {
                    List<Product> productList = data.data;
                    products.addAll(productList);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BaseJSON<List<Product>>> call, Throwable t) {
                Toast.makeText(Ex2Activity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        Product p = data.getParcelableExtra("product");
        if (p == null) {
            return;
        }

        if (requestCode == 1) {
            products.add(0, p);
            adapter.notifyItemInserted(0);

            // TODO: Gọi API để lưu dữ liệu lên server
            addNewProduct(p); // index = 0
        }
        else {
            int position = data.getIntExtra("position", -1);
            if (position < 0) {
                return;
            }
            products.set(position, p);
            adapter.notifyItemChanged(position); // change to blur color

            // TODO: Gọi API để cập nhật dữ liệu lên server
            updateExistingProduct(p, position);
        }
    }

    private void updateExistingProduct(Product p, int position) {

    }

    private void addNewProduct(Product p) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.new_product == item.getItemId()) {
            createNewProduct();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNewProduct() {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int position = item.getGroupId();

        if (item.getItemId() == R.id.edit) {
            editProduct(position);
        }else if (item.getItemId() == R.id.delete) {
            deleteProduct(position);
        }

        return super.onContextItemSelected(item);
    }

    private void editProduct(int position) {
        Product product = products.get(position);
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("product", product);
        intent.putExtra("position", position);

        startActivityForResult(intent, 2);
    }

    private void deleteProduct(int position) {
        Product product = products.get(position);
        new AlertDialog.Builder(this)
                .setTitle("Delete Product")
                .setMessage("Are you sure you want to delete " + product.getName() + "?")
                .setPositiveButton("Delete", (d, w) -> {
                    products.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancel", null)
                .create().show();
    }

    @Override
    public void onProductClick(int position, Product product) {
        editProduct(position);
    }
}
