package com.example.app_recursosintent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.invoke.WrongMethodTypeException;

public class InfoActivity extends AppCompatActivity {

    @Override
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle envelopeLer = getIntent().getExtras();
        String nome = envelopeLer.getString("Nome: ");
        int telefone = envelopeLer.getInt("Tel: ");
        String email = envelopeLer.getString("Email: ");

        EditText resultado = (EditText) findViewById(R.id.resultado);

        resultado.setText(envelopeLer.toString() + "\n\n Separando os dados \n" + nome + "\n" + telefone + "\n" + email);
    }

    public void voltar(View view) {
        Intent in = new Intent(InfoActivity.this, MainActivity.class);
        startActivity(in);
    }
}