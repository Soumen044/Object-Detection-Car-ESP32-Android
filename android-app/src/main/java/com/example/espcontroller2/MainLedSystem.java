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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainLedSystem extends AppCompatActivity {

    Button btnWHITE, btnRED, btnYELLOW;
    TextView txtRES, textview8;

    private SoundPool soundPool;
    private int soundId;

    private final OkHttpClient client = new OkHttpClient();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledsystem_main);

        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.clickselect2_02, 1);


        class ESP8266NetworkManager {

            private final ConnectivityManager connectivityManager;
            private Network espNetwork;

            public ESP8266NetworkManager(Context context) {
                connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            }

            public void bindToESP8266() {
                if (connectivityManager == null) {
                    Log.e("ESP8266NetworkManager", "ConnectivityManager not available");
                    return;
                }
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
                builder.removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);

                NetworkRequest request = builder.build();

                connectivityManager.requestNetwork(request, new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(Network network) {
                        super.onAvailable(network);
                        espNetwork = network;

                        Log.d("ESP8266NetworkManager", "ESP8266 Wi-Fi network available: " + network);
                    }

                    @Override
                    public void onUnavailable() {
                        super.onUnavailable();
                        Log.e("ESP8266NetworkManager", "ESP8266 Wi-Fi network unavailable");
                    }

                    @Override
                    public void onLost(Network network) {
                        super.onLost(network);
                        Log.e("ESP8266NetworkManager", "ESP8266 Wi-Fi network lost");
                        espNetwork = null;
                    }
                });
            }

            public void sendDataToESP(String ipAddress, int port, String data) {
                if (espNetwork == null) {
                    Log.e("ESP8266NetworkManager", "ESP8266 network not available. Cannot send data.");
                    return;
                }
                ESP8266NetworkManager networkManager = new ESP8266NetworkManager(getApplicationContext());
                networkManager.bindToESP8266();

                networkManager.sendDataToESP("192.168.4.1", 80, "Hello ESP8266!");

                networkManager.unbindESPNetwork();

                new Thread(() -> {
                    try (Socket socket = espNetwork.getSocketFactory().createSocket()) {
                        socket.connect(new InetSocketAddress(ipAddress, port), 5000);
                        socket.getOutputStream().write(data.getBytes());
                        socket.getOutputStream().flush();

                        Log.d("ESP8266NetworkManager", "Data sent to ESP8266: " + data);
                    } catch (IOException e) {
                        Log.e("ESP8266NetworkManager", "Error sending data to ESP8266", e);
                    }
                }).start();
            }
            public void unbindESPNetwork() {
                espNetwork = null;
                Log.d("ESP8266NetworkManager", "Unbound from ESP8266 network");
            }
        }

        btnWHITE = findViewById(R.id.btnWHITE);
        btnYELLOW = findViewById(R.id.btnYELLOW);
        btnRED = findViewById(R.id.btnRED);

        txtRES = findViewById(R.id.txtRES);


        btnRED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);

                sendCommand("red");
            }
        });

        btnYELLOW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);

                sendCommand("yellow");
            }
        });

        btnWHITE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            soundPool.play(soundId, 1, 1, 0, 0, 1);


                sendCommand("white");
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ledsystem), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void sendCommand(String cmd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String command = "http://192.168.4.1/" + cmd;
                Log.d("Command---------------------------------------", command);
                Request request = new Request.Builder().url(command).build();
                try{
                    Response response = client.newCall(request).execute();
                    String myResponse = response.body().string();
                    final String cleanResponse = myResponse.replaceAll("\\<.*?\\>", "");
                    cleanResponse.replace("\n", "");
                    cleanResponse.replace("\r", "");
                    cleanResponse.replace(" ", "");
                    cleanResponse.replace("\t", "");
                    cleanResponse.trim();
                    Log.d("Response = ", cleanResponse);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtRES.setText(cleanResponse);
                        }
                    });
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
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