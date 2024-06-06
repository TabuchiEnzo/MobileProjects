package com.example.caraoucoroa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game extends AppCompatActivity {
    ImageView voltar;
    ImageView moeda;
    Random moedaAle = new Random();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        moeda = findViewById(R.id.moedas);
        voltar = findViewById(R.id.voltar);

        int caraCoroa = moedaAle.nextInt(2) + 1;
        //cara=1
        //coroa=2

        // logica para colocar a moeda em numero aleatorio
        if (caraCoroa == 1) {
            moeda.setImageResource(R.drawable.moeda_cara);
        } else {
            moeda.setImageResource(R.drawable.moeda_coroa);
        }

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Game.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}