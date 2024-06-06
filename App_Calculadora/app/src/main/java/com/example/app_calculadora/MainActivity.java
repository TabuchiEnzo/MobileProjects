package com.example.app_calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView resultadoCalc;
    Button btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, soma, subtracao, result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultadoCalc = findViewById(R.id.resultadoCalc);
        btnNum1 = findViewById(R.id.num1);
        btnNum2 = findViewById(R.id.num2);
        btnNum3 = findViewById(R.id.num3);
        btnNum4 = findViewById(R.id.num4);
        btnNum5 = findViewById(R.id.num5);
        btnNum6 = findViewById(R.id.num6);
        btnNum7 = findViewById(R.id.num7);
        btnNum8 = findViewById(R.id.num8);
        btnNum9 = findViewById(R.id.num9);
        soma = findViewById(R.id.soma);
        subtracao = findViewById(R.id.subtracao);
        result = findViewById(R.id.result);


    }

    @SuppressLint("ResourceType")
    public void calculo(View view) {
        double calc = 0;
        Toast.makeText(MainActivity.this, view.getId(), Toast.LENGTH_SHORT).show();
        calc += Double.parseDouble(String.valueOf(view));
    }
}