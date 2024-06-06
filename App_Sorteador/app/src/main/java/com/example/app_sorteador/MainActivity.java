package com.example.app_sorteador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnSortear;
    TextView result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSortear = findViewById(R.id.sortear);
        result = findViewById(R.id.result);

    }

    public void sortear(View view) {
        Random random = new Random();
        int aleatorio = random.nextInt(101);
        String num = Integer.toString(aleatorio);
        result.setText(num);
    }
}