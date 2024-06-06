package com.example.app_recursosintent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    EditText nome, fone, email;
    TextView txt, textInformation;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInformation = findViewById(R.id.btnInfo);

        nome = findViewById(R.id.nome);
        fone = findViewById(R.id.telefone);
        email = findViewById(R.id.email);

        Toast.makeText(this, "Nome: " + nome.getText().toString() + "\nTelefone: " + fone.getText().toString() + "\nEmail: " + email.getText().toString()
                , Toast.LENGTH_SHORT).show();
    }
    public void info(View view){
        //Adicionando info no bundle
        EditText nome = (EditText) findViewById(R.id.nome);
        EditText email = (EditText) findViewById(R.id.email);
        EditText telefone = (EditText) findViewById(R.id.telefone);

        Bundle envelopeDados = new Bundle();
        envelopeDados.putString("nome", nome.getText().toString());
        envelopeDados.putInt("telefone", Integer.parseInt(telefone.getText().toString()));
        envelopeDados.putString("email", email.getText().toString());

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtras(envelopeDados);

        startActivity(intent);
    }
    public void discar(View view) {
        EditText fone = (EditText) findViewById(R.id.telefone);
        Intent in = new Intent(Intent.ACTION_DIAL);

        in.setData(Uri.parse("Tel: " + fone.getText().toString()));

        startActivity(in);
    }

    public void sendEmail(View view) {
        EditText email = (EditText) findViewById(R.id.email);

        Intent in = new Intent(Intent.ACTION_SEND);
        in.setType("text/html");
        in.putExtra(Intent.EXTRA_EMAIL, new String[] {email.getText().toString()});
        in.putExtra(Intent.EXTRA_SUBJECT, "Suporte App");
        in.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<html><body><h1>Corpo da mensagem<h1></body></html>", 1));

        startActivity(in);
    }
}