package com.example.app_youwinbuttondesign;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void caixaMsg(View view) {
        // usando dialog
        Dialog caixaMsg = new Dialog(this);
        caixaMsg.setContentView(R.layout.custom1); // definir o que vai carregar
        caixaMsg.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT); // setando o height e o width
        caixaMsg.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        caixaMsg.setCancelable(false); // nao deixando clicar fora da caixa

        TextView txtTitulo = caixaMsg.findViewById(R.id.titulo); // pegando o titulo no res
        txtTitulo.setText("ANDROIND");

        TextView txtMensagem = caixaMsg.findViewById(R.id.msg); // pegando o titulo no res
        txtMensagem.setText("NAOOOOOOOOOOOO SIMMMMMMM");

        // Obter o comportamento dos botões
        Button btnSim = caixaMsg.findViewById(R.id.botaoSim);
        Button btnNao = caixaMsg.findViewById(R.id.botaoNao);
        Button btnX = caixaMsg.findViewById(R.id.x);

        btnSim.setText("SIM");
        btnNao.setText("NÃO");

        btnSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Você disse 'SIM'", Toast.LENGTH_SHORT).show();
            }
        });

        btnNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Você disse 'NÃO'", Toast.LENGTH_SHORT).show();
            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caixaMsg.setCancelable(true);
            }
        });
        // carregar
        caixaMsg.show();
    }
}