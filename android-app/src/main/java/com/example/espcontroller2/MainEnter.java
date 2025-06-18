package com.example.espcontroller2;


import static androidx.core.content.ContextCompat.startActivity;
import android.Manifest;
import android.content.pm.PackageManager;  // Ensure this exists too
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainEnter extends AppCompatActivity {

    private TextView textView1, textView2;
    private ImageView myImageView2;
    private Button Start;

    private SoundPool soundPool;
    private int soundId;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_main);

        checkLocationPermission();

        // Initialize SoundPool
        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        // Find the button
        Start = findViewById(R.id.button1);

        // Set an OnClickListener
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play the sound
                soundPool.play(soundId, 1, 1, 0, 0, 1);

                // Check if Wi-Fi is enabled
                if (isWifiEnabled()) {
                    // Navigate to MainContinue activity
                    Intent intent = new Intent(MainEnter.this, MainContinue.class);
                    startActivity(intent);
                } else {
                    // Show Wi-Fi enable dialog
                    showWifiEnableDialog();
                }
            }
        });

    }

    private static final int LOCATION_PERMISSION_CODE = 100;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE);
        }
    }
    private boolean isWifiEnabled() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    private void showWifiEnableDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Wi-Fi Required")
                .setMessage("Please turn on Wi-Fi to proceed.")
                .setPositiveButton("OK", (dialog, which) -> {
                    startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(MainEnter.this, "Wi-Fi is required to continue.", Toast.LENGTH_SHORT).show();
                })
                .show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release SoundPool resources
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
