package com.example.app_conversor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//IMPORTAR API JSON
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity<RequestQueue> extends AppCompatActivity {
    boolean invertOnOff = true;
    Button btnConverter, btnInverter;
    EditText editValor;
    TextView saidaValor, informeValor, resultValor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConverter = findViewById(R.id.btnConverter);
        btnInverter = findViewById(R.id.btnInverter);
        editValor = findViewById(R.id.editEntrada);
        saidaValor = findViewById(R.id.editSaida);
        informeValor = findViewById(R.id.informeValor);
        resultValor = findViewById(R.id.resultValor);

        btnInverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invertOnOff) {
                    invertOnOff = false;
                    informeValor.setText("Informe o valor em Reais");
                    resultValor.setText("Valor em $");
                } else {
                    invertOnOff = true;
                    informeValor.setText("Informe o valor em Dolar");
                    resultValor.setText("Valor em R$");
                }

                converterAPI(v);
                double dolar = 4.95;
                if(invertOnOff) {
                    Double valorDolar = Double.parseDouble(editValor.getText().toString());
                    Double valorConvertido = valorDolar * dolar;
                    String result = String.format("R$ %.2f", valorConvertido);
                    saidaValor.setText(result);
                }else{
                    Double valorReal = Double.parseDouble(editValor.getText().toString());
                    Double valorConvertido = valorReal / dolar;
                    String result = String.format("$ %.2f", valorConvertido);
                    saidaValor.setText(result);
                }
            }
        });
    };
    public void converterAPI(View view){
        String url = "https://economia.awesomeapi.com.br/json/last/USD-BRL";
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Processando a resposta da API
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject currencyData = jsonObject.getJSONObject("USDBRL");

                    if(currencyData.has("bid")){
                        String cotacao = currencyData.getString("bid");

                        if(invertOnOff) {
                            Double valorDolar = Double.parseDouble(editValor.getText().toString());
                            Double valorConvertido = valorDolar * Double.valueOf(cotacao);
                            String result = String.format("R$ %.2f", valorConvertido);
                            saidaValor.setText(result);
                        }else{
                            Double valorReal = Double.parseDouble(editValor.getText().toString());
                            Double valorConvertido = valorReal / Double.valueOf(cotacao);
                            String result = String.format("$ %.2f", valorConvertido);
                            saidaValor.setText(result);
                        }

                    }

                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Aqui você pode tratar o erro da requisição
                error.printStackTrace();
            }
        });
        ((com.android.volley.RequestQueue) queue).add(stringRequest);
    }
}