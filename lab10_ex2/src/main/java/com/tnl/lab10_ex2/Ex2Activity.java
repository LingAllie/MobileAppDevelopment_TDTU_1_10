package com.tnl.lab10_ex2;

import androidx.annotation.NonNull;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;


import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.location.Location;

public class Ex2Activity extends BaseActivity {

    private FusedLocationProviderClient client;
    private TextView latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        latitude = findViewById(R.id.tvLatitude);
        longitude = findViewById(R.id.tvLongitude);

        client = LocationServices.getFusedLocationProviderClient(this);

        if (hasLocationPermission()) {
            getLastKnowLocation();
        } else {
            requestLocationPermission(1);
        }
    }

    private void getLastKnowLocation() {
        try {
            client.getLastLocation()
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            latitude.setText(String.valueOf(location.getLatitude()));
                            longitude.setText(String.valueOf(location.getLongitude()));
                            Log.e("TAG", location.getLatitude() + ", " + location.getLongitude());
                        } else {
                            Log.e("TAG", "Location is not found");
                        }
                    })
                    .addOnFailureListener(e -> {
                        e.printStackTrace();
                        Log.e("TAG", "Error: " + e.getMessage());
                    });
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            Toast.makeText(this, "No location permission", Toast.LENGTH_SHORT).show();
        }
    }
}