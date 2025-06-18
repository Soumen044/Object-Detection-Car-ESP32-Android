package com.example.espcontroller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.media.SoundPool;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

public class MainStartESPCar extends AppCompatActivity {
    private Button Start2;
    private SoundPool soundPool;
    private int soundId;
    private DatabaseReference connectionRef;
    private ValueEventListener connectionListener;
    private Handler heartbeatHandler;
    private Runnable heartbeatRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startespcar_main);

        // Initialize Firebase
        connectionRef = FirebaseDatabase.getInstance().getReference("activeConnection");

        // Initialize UI
        Start2 = findViewById(R.id.button05);

        // Initialize SoundPool
        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        // Setup connection monitoring
        setupConnectionMonitoring();

        // Setup heartbeat
        setupHeartbeat();

        Start2.setOnClickListener(v -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            startActivity(new Intent(this, MainESPCar.class));
        });

        setupLinkButtons();
    }

    private void setupConnectionMonitoring() {
        connectionListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(MainStartESPCar.this,
                            "Connection lost", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Connection", "Monitoring cancelled", error.toException());
            }
        };
        connectionRef.addValueEventListener(connectionListener);
    }

    private void setupHeartbeat() {
        heartbeatHandler = new Handler();
        heartbeatRunnable = new Runnable() {
            @Override
            public void run() {
                connectionRef.child("timestamp").setValue(ServerValue.TIMESTAMP)
                        .addOnFailureListener(e -> Log.e("Heartbeat", "Failed to update timestamp"));
                heartbeatHandler.postDelayed(this, 30000); // Update every 30 seconds
            }
        };
        heartbeatHandler.post(heartbeatRunnable);
    }

    private void setupLinkButtons() {
        // Your existing link button code
        // ...
    }

    @Override
    protected void onDestroy() {
        // Clean up listeners and handlers
        if (connectionRef != null && connectionListener != null) {
            connectionRef.removeEventListener(connectionListener);
        }
        if (heartbeatHandler != null && heartbeatRunnable != null) {
            heartbeatHandler.removeCallbacks(heartbeatRunnable);
        }

        // Remove active connection
        connectionRef.removeValue();

        // Release SoundPool
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }

        super.onDestroy();
    }
}