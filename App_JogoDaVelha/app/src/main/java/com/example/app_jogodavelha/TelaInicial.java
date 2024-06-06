package com.example.app_jogodavelha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TelaInicial extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }

    // Iniciar Activity do jogo em modo dois jogadores
    public void amigo(View view) {
        Intent intent = new Intent(TelaInicial.this, Game.class);
        intent.putExtra("MODE", "AMIGO");
        startActivity(intent);
    }

    // Iniciar Activity do jogo em modo contra o bot
    public void bot(View view) {
        Intent intent = new Intent(TelaInicial.this, Game.class);
        intent.putExtra("MODE", "BOT");
        startActivity(intent);
    }
}