package com.example.espcontroller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConnectionCheckActivity extends AppCompatActivity {
    private TextView statusText;
    private Button continueBtn;
    private SoundPool soundPool;
    private int soundId;
    private DatabaseReference connectionRef;
    private ValueEventListener connectionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connectionesp_main);

        // Initialize Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        connectionRef = FirebaseDatabase.getInstance().getReference("activeConnection");

        // Initialize UI
        statusText = findViewById(R.id.statusText);
        continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setVisibility(View.GONE);

        // Initialize SoundPool
        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        continueBtn.setOnClickListener(v -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            startActivity(new Intent(this, DatabaseAuthActivity.class));
        });

        setupConnectionMonitoring();
    }

    private void setupConnectionMonitoring() {
        connectionListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String email = snapshot.child("email").getValue(String.class);
                    long timestamp = snapshot.child("timestamp").getValue(Long.class);

                    // Check if connection is stale (>5 minutes)
                    if (System.currentTimeMillis() - timestamp > 300000) {
                        statusText.setText("Stale connection detected");
                        continueBtn.setVisibility(View.VISIBLE);
                        connectionRef.removeValue();
                    } else {
                        statusText.setText("Connected to: " + email);
                        continueBtn.setVisibility(View.GONE);
                    }
                } else {
                    statusText.setText("Available to connect!");
                    continueBtn.setVisibility(View.VISIBLE);
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                statusText.setText("Error checking connection");
            }
        };
        connectionRef.addValueEventListener(connectionListener);
    }

    @Override
    protected void onDestroy() {
        if (connectionRef != null && connectionListener != null) {
            connectionRef.removeEventListener(connectionListener);
        }
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        super.onDestroy();
    }
}