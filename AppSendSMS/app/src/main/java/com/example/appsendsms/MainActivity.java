package com.example.appsendsms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.app.appsearch.PackageIdentifier;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText telefone, mensagem;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        telefone = findViewById(R.id.telefone);
        mensagem = findViewById(R.id.mensagem);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Preparando para rodar em 2° plano
                Intent in = new Intent(getApplicationContext().toString());
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, in, PendingIntent.FLAG_IMMUTABLE);

                // Carregar um gerenciador de telas
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(telefone.getText().toString(), null,
                        mensagem.getText().toString(), pi, null);
                Toast.makeText(MainActivity.this, "FOI !!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}