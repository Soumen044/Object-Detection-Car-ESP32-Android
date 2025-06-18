package com.example.espcontroller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DatabaseAuthActivity extends AppCompatActivity {
    private EditText emailInput, nameInput;
    private Button connectBtn;
    private DatabaseReference dbRef;
    private SoundPool soundPool;
    private int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databaseesp_main);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        dbRef = FirebaseDatabase.getInstance().getReference("activeConnection");

        // Initialize UI
        emailInput = findViewById(R.id.emailInput);
        nameInput = findViewById(R.id.nameInput);
        connectBtn = findViewById(R.id.connectBtn);

        // Initialize SoundPool
        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        connectBtn.setOnClickListener(v -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            connectToCar();
        });
    }

    private void connectToCar() {
        String email = emailInput.getText().toString().trim();
        String name = nameInput.getText().toString().trim();

        if (email.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Please enter email and name", Toast.LENGTH_SHORT).show();
            return;
        }

        dbRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.@NonNull Result doTransaction(@NonNull MutableData mutableData) {
                if (mutableData.getValue() != null) {
                    return Transaction.success(mutableData);
                }

                Map<String, Object> user = new HashMap<>();
                user.put("email", email);
                user.put("name", name);
                user.put("timestamp", ServerValue.TIMESTAMP);
                mutableData.setValue(user);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                if (error != null) {
                    Toast.makeText(DatabaseAuthActivity.this,
                            "Connection error", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!committed) {
                    Toast.makeText(DatabaseAuthActivity.this,
                            "Connection already taken!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(DatabaseAuthActivity.this, MainStartESPCar.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        super.onDestroy();
    }
}