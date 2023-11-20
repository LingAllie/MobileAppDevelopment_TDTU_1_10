package com.tnl.lab10_ex1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if (getIntent().hasExtra("data")) {
            ContactUser contactUser = getIntent().getParcelableExtra("data");
            displayContactDetails(contactUser);
        }
    }

    private void displayContactDetails(ContactUser contactUser) {
        TextView nameTextView = findViewById(R.id.fullNameTextView);
        nameTextView.setText(contactUser.getName());

        TextView phoneNumbersTextView = findViewById(R.id.phoneNumbersTextView);
        List<String> phoneNumbers = contactUser.getPhone();
        if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
            StringBuilder phoneNumbersText = new StringBuilder();
            for (String phoneNumber : phoneNumbers) {
                phoneNumbersText.append(phoneNumber).append("\n");
            }
            phoneNumbersTextView.setText(phoneNumbersText.toString());
        }

        TextView addressTextView = findViewById(R.id.addressTextView);
        addressTextView.setText(contactUser.getAddress());

        TextView emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText(contactUser.getEmail());

        TextView noteTextView = findViewById(R.id.noteTextView);
        noteTextView.setText(contactUser.getNote());

        TextView websiteTextView = findViewById(R.id.addressHomeTextView);
        websiteTextView.setText(contactUser.getWebsite());
    }
}
