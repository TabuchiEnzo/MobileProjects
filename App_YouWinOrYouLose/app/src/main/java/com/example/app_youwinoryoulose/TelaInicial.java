package com.example.app_youwinoryoulose;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }

    public void sim() {
        // usando dialog
        final Dialog sim = new Dialog(this);
        sim.setContentView(R.layout.activity_popup); // definir o que vai carregar
        sim.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); // setando o height e o width
        sim.setCancelable(false); // nao deixando clicar fora da caixa

        Button win = sim.findViewById(R.id.win);
        Button lose = sim.findViewById(R.id.lose);
        Button voltar = sim.findViewById(R.id.voltar);
        ImageView img = sim.findViewById(R.id.img);

        win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.winimg);
            }
        });

        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.loseimg);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Feche o diálogo ao clicar em "Voltar"
                sim.dismiss();
            }
        });

        sim.show();
    }
}