package com.example.apprecycleview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvNota;
    FloatingActionButton btMais;
    View popup;
    Button adicionar, cancelar;
    EditText inserirTitulo, inserirDescricao;
    List<Nota> listaNota = new ArrayList<>();
    DatabaseSalaF databaseSalaF = new DatabaseSalaF();

    // carregar o firebase
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNota = findViewById(R.id.recyclerView);
        btMais = findViewById(R.id.floatingActionButton);
        popup = findViewById(R.id.popup);

        AdapterNota adapterNota = new AdapterNota(listaNota);
        rvNota.setAdapter(adapterNota);

//        // adicionar as notas
//        db.collection("notas").document("1").set(new Nota("Aula1", "Aprendendo sobre o Firebase 1"));
//        db.collection("notas").document("2").set(new Nota("Aula2", "Aprendendo sobre o Firebase 2"));
//        db.collection("notas").document().set(new Nota("Aula3", "Aprendendo sobre o Firebase 3"));
//
//        //  remover as notas
//        db.collection("notas").document("1").delete();
//        db.collection("notas").document("2").delete();

        databaseSalaF.listar(listaNota, adapterNota);

        // ler as notas
        db.collection("notas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        Nota nota = document.toObject(Nota.class);
                        listaNota.add(nota);
                        adapterNota.notifyItemInserted(listaNota.size());
                    }
                }
            }
        });

        btMais.setOnClickListener(new View.OnClickListener()    {
            @Override
            public void onClick(View v) {
                // Inflar o popup
                View popup = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup, null);

                // Agora você pode mostrar o popup de alguma forma, por exemplo, usando um AlertDialog ou diretamente adicionando à hierarquia de views
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(popup);
                AlertDialog dialog = builder.create();

                adicionar = popup.findViewById(R.id.adicionar);
                cancelar = popup.findViewById(R.id.cancel);
                inserirTitulo = popup.findViewById(R.id.inserirTitulo);
                inserirDescricao = popup.findViewById(R.id.inserirDescricao);

                adicionar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listaNota.add(new Nota(inserirTitulo.getText().toString(), inserirDescricao.getText().toString()));
                        AdapterNota adapterNota = new AdapterNota(listaNota);
                        rvNota.setAdapter(adapterNota);
                        db.collection("notas").document().set(new Nota(inserirTitulo.getText().toString(), inserirDescricao.getText().toString()));
                        dialog.dismiss(); // Fechar o popup após adicionar
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // Fechar o popup ao cancelar
                    }
                });

                dialog.show();
            }
        });

        // aparecer o grid do recycle view
        rvNota.setLayoutManager(new GridLayoutManager(this, 3));
    }
}