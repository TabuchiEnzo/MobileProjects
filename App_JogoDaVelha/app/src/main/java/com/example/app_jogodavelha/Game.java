package com.example.app_jogodavelha;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private boolean jogador1 = true;
    private MediaPlayer somClick;
    private int contadorRound;
    private boolean verificadorBot; // variavel para indicar se está jogando contra o bot

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        // Inicialização do MediaPlayer com o som de clique
        somClick = MediaPlayer.create(this, R.raw.click_sound);

        // Identificar modo de jogo da Intent
        Intent intent = getIntent();
        String mode = intent.getStringExtra("MODE");
        verificadorBot = "BOT".equals(mode) || "BOT".equals(mode.toUpperCase());

        // Adicione OnClickListener a cada célula do jogo
        findViewById(R.id.img1).setOnClickListener(this);
        findViewById(R.id.img2).setOnClickListener(this);
        findViewById(R.id.img3).setOnClickListener(this);
        findViewById(R.id.img4).setOnClickListener(this);
        findViewById(R.id.img5).setOnClickListener(this);
        findViewById(R.id.img6).setOnClickListener(this);
        findViewById(R.id.img7).setOnClickListener(this);
        findViewById(R.id.img8).setOnClickListener(this);
        findViewById(R.id.img9).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Reproduza o som quando o botão for clicado
        if (somClick != null) {
            somClick.start();
        }

        // Identifique qual célula foi clicada e atualize conforme necessário
        int idClicado = v.getId();
        ImageView imageView = findViewById(idClicado);
        if (imageView.getDrawable() == null) {
            if (jogador1) {
                imageView.setImageResource(R.drawable.x);
            } else {
                imageView.setImageResource(R.drawable.o);
            }

            contadorRound++;

            if (verificarVencedor()) {
                if (jogador1) {
                    vencedor("Player 1");
                } else {
                    vencedor(verificadorBot ? "Bot" : "Player 2");
                }
            } else if (contadorRound == 9) {
                perdedor();
            } else {
                jogador1 = !jogador1;
                if (verificadorBot && !jogador1) {
                    // Se jogando contra o bot, o bot faz sua jogada
                    movimentoBot();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (somClick != null) {
            somClick.release();
        }
    }

    // Método para voltar para a tela inicial
    public void voltar(View view) {
        Intent in = new Intent(Game.this, TelaInicial.class);
        startActivity(in);
    }

    // Método para avisar quem venceu
    private void vencedor(String player) {
        Toast.makeText(this, player + " Venceu!", Toast.LENGTH_SHORT).show();
        resetar();
    }

    // Método para avisar quem perdeu
    private void perdedor() {
        Toast.makeText(this, "Perdeu!", Toast.LENGTH_SHORT).show();
        resetar();
    }

    // Método para resetar
    private void resetar() {
        // Limpa o conteúdo de todas as células do jogo
        for (int i = 1; i <= 9; i++) {
            int resID = getResources().getIdentifier("img" + i, "id", getPackageName());
            ImageView imageView = findViewById(resID);
            imageView.setImageDrawable(null);
        }

        contadorRound = 0;
        jogador1 = true;
    }

    // Método para calcular o movimento do bot
    private void movimentoBot() {
        // Lista para armazenar índices das células vazias
        List<Integer> celulasVazias = new ArrayList<>();

        // Adiciona os índices das células vazias à lista
        for (int i = 1; i <= 9; i++) {
            int resID = getResources().getIdentifier("img" + i, "id", getPackageName());
            ImageView imageView = findViewById(resID);
            if (imageView.getDrawable() == null) {
                celulasVazias.add(i);
            }
        }

        // Verifica se há uma oportunidade de vitória imediata para o bot
        for (int index : celulasVazias) {
            // Simula a jogada do bot
            int resID = getResources().getIdentifier("img" + index, "id", getPackageName());
            ImageView imageView = findViewById(resID);
            imageView.setImageResource(R.drawable.o);

            // Verifica se essa jogada resulta em vitória para o bot
            if (verificarVencedor()) {
                vencedor("Bot");
                return;
            } else {
                // Reverte a jogada
                imageView.setImageDrawable(null);
            }
        }

        // Se não houver oportunidade de vitória imediata, faz um movimento aleatório
        if (!celulasVazias.isEmpty()) {
            // Seleciona aleatoriamente uma célula vazia para jogar
            Random random = new Random();
            int indexAleatorio = celulasVazias.get(random.nextInt(celulasVazias.size()));
            int resID = getResources().getIdentifier("img" + indexAleatorio, "id", getPackageName());
            ImageView imageView = findViewById(resID);
            imageView.setImageResource(R.drawable.o);
        } else {
            // Caso não haja mais células vazias, o jogo está empatado
            perdedor();
        }
    }

    // Método para verificar o vencedor
    private boolean verificarVencedor() {
        // Lista para armazenar os índices das células marcadas por cada jogador
        List<Integer> celulasJogador1 = new ArrayList<>();
        List<Integer> celulasJogador2 = new ArrayList<>();

        // Adiciona os índices das células marcadas por cada jogador às listas correspondentes
        for (int i = 1; i <= 9; i++) {
            int resID = getResources().getIdentifier("img" + i, "id", getPackageName());
            ImageView imageView = findViewById(resID);
            if (imageView.getDrawable() != null) {
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.x).getConstantState())) {
                    celulasJogador1.add(i);
                } else {
                    celulasJogador2.add(i);
                }
            }
        }

        // Array contendo todas as combinações vencedoras
        int[][] combinacoesVencedoras = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, // Linhas
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, // Colunas
                {1, 5, 9}, {3, 5, 7}              // Diagonais
        };

        // Verifica se algum jogador completou alguma das combinações vencedoras
        for (int[] combinacao : combinacoesVencedoras) {
            if (celulasJogador1.contains(combinacao[0]) && celulasJogador1.contains(combinacao[1]) && celulasJogador1.contains(combinacao[2])) {
                return true; // Jogador 1 venceu
            } else if (celulasJogador2.contains(combinacao[0]) && celulasJogador2.contains(combinacao[1]) && celulasJogador2.contains(combinacao[2])) {
                return true; // Jogador 2 (ou Bot) venceu
            }
        }

        return false; // Nenhum jogador venceu
    }
}