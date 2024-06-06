package com.example.app_combustivel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.Format;

public class MainActivity extends AppCompatActivity {
    TextInputEditText editPrecoAlcool, editPrecoGasolina;
    Button btnCalcular;
    TextView resultado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPrecoAlcool = findViewById(R.id.editPeso);
        editPrecoGasolina = findViewById(R.id.editAltura);
        btnCalcular = findViewById(R.id.calcular);
        resultado = findViewById(R.id.resultado);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double precoAlcool = Double.parseDouble(editPrecoAlcool.getText().toString());

                double precoGasolina = Double.parseDouble(editPrecoGasolina.getText().toString());

                DecimalFormat df = new DecimalFormat();
                df.applyPattern("R$ #,##0.00");

                double calculo = (precoAlcool/precoGasolina);
                if(calculo >= 0.7){
                    resultado.setText("Use Gasolina \nEstá " + df.format(calculo) + "% melhor!");
                }else{
                    resultado.setText("Use Álcool \nEstá " + df.format(calculo) + "% melhor!");
                }
            }
        });
    }
}