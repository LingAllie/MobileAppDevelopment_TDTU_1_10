package com.tnl.lab10_ex2;

import androidx.annotation.NonNull;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;


import com.google.android.gms.location.LocationServices;

public class Ex2Activity extends BaseActivity {

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

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
            // Handle the exception gracefully, such as showing a message to the user
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