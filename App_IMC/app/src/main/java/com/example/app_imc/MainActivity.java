package com.example.app_imc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText editPeso, editAltura;
    Button btnCalcular;
    TextView resultado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);
        btnCalcular = findViewById(R.id.btnCalcular);
        resultado = findViewById(R.id.resultadoIMC);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double peso = Double.parseDouble(editPeso.getText().toString());
                double altura = Double.parseDouble(editAltura.getText().toString());
                double imc = peso / (altura * altura);
                if (imc > 40) {
                    resultado.setText("Obesidade grau III\n Bora marcar uma academia!");
                } else if (imc >= 35) {
                    resultado.setText("Obesidade grau II\n Procure um nutricionista!");
                } else if (imc >= 30) {
                    resultado.setText("Obesidade grau I\n Você está obeso. Largue essa pizza! ");
                } else if (imc >= 25) {
                    resultado.setText("Acima do peso\n Uma corridinha de vez em quando cai bem!");
                } else if (imc >= 18.5) {
                    resultado.setText("Peso normal\n Você está indo bem!");
                } else if (imc >= 17) {
                    resultado.setText("Abaixo do peso\n Bota mais comida nesse prato!");
                } else if (imc < 17) {
                    resultado.setText("Muito abaixo do peso\n Cuidado para não ser levado com o vento!");
                }
            }
        });
    }
}