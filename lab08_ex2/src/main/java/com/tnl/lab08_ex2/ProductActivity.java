package com.tnl.lab08_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class ProductActivity extends AppCompatActivity {

    private Button btnSave;
    private TextInputEditText txtName, txtPrice, txtDesc;

    private Product product;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setTitle("Add New Product");

        btnSave = findViewById(R.id.btnSave);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDesc = findViewById(R.id.txtDesc);

        product = getIntent().getParcelableExtra("product");
        position = getIntent().getIntExtra("position", -1);
        if (product != null) {

            txtName.setText(product.getName());
            txtPrice.setText(product.getPrice() + "");
            txtDesc.setText(product.getDescription());
        }
    }

    public void saveProduct(View view) {
        String name = txtName.getText().toString();
        String priceText = txtPrice.getText().toString();
        String desc = txtDesc.getText().toString();

        int price = Integer.parseInt(priceText);

        if (product == null) {
            product = new Product();
        }
        product.setLoading(true);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(desc);

        Intent intent = new Intent();
        intent.putExtra("product", product);
        intent.putExtra("position", position);


        setResult(RESULT_OK, intent);
        finish();
    }
}