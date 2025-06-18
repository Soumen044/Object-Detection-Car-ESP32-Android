package com.example.espcontroller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainContinue extends AppCompatActivity {
    private TextView textView1, textView2;
    private ImageView myImageView2;
    @SuppressLint("StaticFieldLeak")
    private static Button Start;

    private SoundPool soundPool;
    private int soundId;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_main);


        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        Start= findViewById(R.id.button2);


        Start.setOnClickListener((v) -> {


            soundPool.play(soundId, 1, 1, 0, 0, 1);

            Intent ht1 = new Intent (MainContinue.this, MainMenu.class);
            startActivity(ht1);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.continu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }

}