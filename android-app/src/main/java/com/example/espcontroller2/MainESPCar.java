package com.example.espcontroller2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.content.Intent;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainESPCar extends AppCompatActivity {

    // initialising ui components
    Button exit01;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    TextView txtRES01;
    ImageButton forward, left, stop, right, backward;

    private SoundPool soundPool;
    private int soundId;

    private final OkHttpClient client = new OkHttpClient();

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final int currentDistance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espcar_main);

        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        // Initializing UI Elements by id
        exit01 = findViewById(R.id.exit01);
        forward = findViewById(R.id.forward);
        left = findViewById(R.id.left);
        stop = findViewById(R.id.stop);
        right = findViewById(R.id.right);
        backward = findViewById(R.id.backward);
        txtRES01 = findViewById(R.id.txtRES01);

        exit01.setOnClickListener((v) -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            Intent ht1 = new Intent(MainESPCar.this, MainMenu.class);
            startActivity(ht1);
        });

        // control buttons for car movement
        forward.setOnClickListener(view -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            sendCommand("forward");
        });

        left.setOnClickListener(view -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            sendCommand("left");
        });

        stop.setOnClickListener(view -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            sendCommand("stop");
        });

        right.setOnClickListener(view -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            sendCommand("right");
        });

        backward.setOnClickListener(view -> {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            sendCommand("backward");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.espcar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Function to send command to NodeMCU
    public void sendCommand(String cmd) {
        new Thread(() -> {
            String command = "http://192.168.4.1/" + cmd;
            Log.d("Command---------------------------------------", command);
            Request request = new Request.Builder().url(command).build();
            try {
                Response response = client.newCall(request).execute();
                String myResponse = response.body().string();
                final String cleanResponse = myResponse.replaceAll("\\<.*?\\>", "").trim();
                Log.d("Response = ", cleanResponse);

                // Update the command result in the UI
                runOnUiThread(() -> txtRES01.setText(cleanResponse));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
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

