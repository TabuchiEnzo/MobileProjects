package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText texto;
    Button adicionar;
    ListView lista;

    ArrayAdapter<String> adapter;

    List<String> lembretes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto);
        adicionar = findViewById(R.id.add);
        lista = findViewById(R.id.lista);

        lembretes = new ArrayList<String>();

        // configurar o componente ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lembretes);
        lista.setAdapter(adapter);

        // pegar o click
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Apaguei a posição " + position, Toast.LENGTH_SHORT).show();
                lembretes.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void adicionar(View view) {
        lembretes.add(texto.getText().toString());
        texto.setText("");
        adapter.notifyDataSetChanged();
    }
}