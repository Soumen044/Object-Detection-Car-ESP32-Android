package com.example.espcontroller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenu extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static ImageButton Start1, Start2, goback, settings;
    private Button checkbutton09, checkbutton10;
    private SoundPool soundPool;
    private int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle savedInstanceState1 = savedInstanceState;
        super.onCreate(savedInstanceState1);
        setContentView(R.layout.menu_main);

        // Initializing Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);// Optional: for offline support

        Start1 = findViewById(R.id.btntoledsystem);

        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        Start1.setOnClickListener((v) -> {

            soundPool.play(soundId, 1, 1, 0, 0, 1);

            Intent ht1 = new Intent(MainMenu.this, MainStartLed.class);
            startActivity(ht1);
        });

        Start2 = findViewById(R.id.btntoconnectiondbespcar);

        Start2.setOnClickListener((v) -> {

            soundPool.play(soundId, 1, 1, 0, 0, 1);

            Intent ht1 = new Intent(MainMenu.this, ConnectionCheckActivity.class);
            startActivity(ht1);
        });

        goback = findViewById(R.id.goback);

        goback.setOnClickListener((v) -> {

            soundPool.play(soundId, 1, 1, 0, 0, 1);

            Intent ht1 = new Intent(MainMenu.this, MainContinue.class);
            startActivity(ht1);
        });

        settings = findViewById(R.id.settings);

        settings.setOnClickListener((v) -> {

            soundPool.play(soundId, 1, 1, 0, 0, 1);

            Intent ht1 = new Intent(MainMenu.this, Settings.class);
            startActivity(ht1);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}