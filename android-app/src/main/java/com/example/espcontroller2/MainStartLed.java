package com.example.espcontroller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainStartLed extends AppCompatActivity {
    private TextView textView6, textView7, textView8, textView9;
    private EditText ipxx1;
    @SuppressLint("StaticFieldLeak")
    private static Button Start;

    private SoundPool soundPool;
    private int soundId;

    public static String texr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startled_main);

        Start= findViewById(R.id.button5);
        ipxx1= findViewById(R.id.ipadd);

        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);

        Start.setOnClickListener((v) -> {

            soundPool.play(soundId, 1, 1, 0, 0, 1);

            texr=ipxx1.getText().toString();
            Intent ht1 = new Intent (MainStartLed.this,MainLedSystem.class);
            startActivity(ht1);
        });

        ImageButton openLinkImageButton = findViewById(R.id.circuitdiagramledsystem);
        openLinkImageButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
                String url = "https://google.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        openLinkImageButton = findViewById(R.id.arduinocodeledsystem);
        openLinkImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
                String url = "https://youtube.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        openLinkImageButton = findViewById(R.id.documentledsystem);
        openLinkImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                String url = "https://youtube.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.startled), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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