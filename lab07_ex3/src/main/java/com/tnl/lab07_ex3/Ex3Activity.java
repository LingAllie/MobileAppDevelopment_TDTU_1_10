package com.tnl.lab07_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ex3Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtValue;
    private TextView tvContent;
    private Button btnWI, btnRI, btnWEP, btnREP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);

        initViews();
    }

    private void initViews() {
        txtValue = findViewById(R.id.txtValue);
        tvContent = findViewById(R.id.tvContent);
        btnWI = findViewById(R.id.btnWI);
        btnRI = findViewById(R.id.btnRI);
        btnWEP = findViewById(R.id.btnWEP);
        btnREP = findViewById(R.id.btnREP);

        btnWI.setOnClickListener(this);
        btnRI.setOnClickListener(this);
        btnWEP.setOnClickListener(this);
        btnREP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnRI) {
            try {
                readInternal();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (id == R.id.btnWI) {
            writeInternal();
        } else if (id == R.id.btnREP) {
            readExternalPrivate();
        } else if (id == R.id.btnWEP) {
            writeExternalPrivate();
        }
    }

    private void writeExternalPrivate() {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "private_data.txt");
        String value = txtValue.getText().toString();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., file not found
            Toast.makeText(this, "Error writing to the file", Toast.LENGTH_SHORT).show();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    private void readExternalPrivate() {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "private_data.txt");

        try {
            FileInputStream stream = new FileInputStream(file);
            Scanner scanner = new Scanner(stream);

            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            String value = content.toString().trim(); // Remove trailing newline characters

            tvContent.setText(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., file not found
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }
    }




    private void writeInternal() {
        FileOutputStream file = null;
        try {
            file = openFileOutput("data.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String value = txtValue.getText().toString();

        PrintWriter writer = new PrintWriter(file);
        writer.println(value);

        writer.close();

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

    }

    private void readInternal() throws FileNotFoundException {
        InputStream stream = openFileInput("data.txt");
        Scanner scanner = new Scanner(stream);

        String value = scanner.nextLine();
        scanner.close();

        tvContent.setText(value);
    }

}