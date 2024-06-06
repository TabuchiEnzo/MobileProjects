package com.aulas.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgSplash = findViewById(R.id.imgSplash);

        // usando o glide

        Glide.with(this).load("https://i.gifer.com/5yE.gif").centerCrop().into(imgSplash);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirSeguntaTela();
            }
        }, 8000);
    }

    public void abrirSeguntaTela() {
        Intent intent = new Intent(MainActivity.this, SegundaTela.class);
        startActivity(intent);
        finish();
    }
}