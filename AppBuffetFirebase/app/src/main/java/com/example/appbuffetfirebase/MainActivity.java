package com.example.appbuffetfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addButton;
    Button adicionar, cancelar;
    EditText nomeCrianca, nomeResponsavel, telefone, dtNasc;
    RecyclerView recyclerView;
    View formPopup;
    List<Crianca> listaCrianca = new ArrayList<>();
    AdapterCrianca myAdapter;

    DatabaseCrianca databaseCrianca = new DatabaseCrianca();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        formPopup = findViewById(R.id.form);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new AdapterCrianca(listaCrianca);
        recyclerView.setAdapter(myAdapter);

        databaseCrianca.listar(listaCrianca, myAdapter);

        db.collection("crianca").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Crianca crianca = document.toObject(Crianca.class);
                        listaCrianca.add(crianca);
                        myAdapter.notifyItemInserted(listaCrianca.size());
                    }
                }
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myAdapter.simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Configuração do botão de adicionar
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar o layout do formulário
                View formPopup = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_formulario, null);

                // Cria e configura o AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(formPopup);
                AlertDialog dialog = builder.create();

                // Inicializa os componentes do formulário
                adicionar = formPopup.findViewById(R.id.btnSalvar);
                cancelar = formPopup.findViewById(R.id.btnCancel);
                nomeCrianca = formPopup.findViewById(R.id.nomeCrianca);
                nomeResponsavel = formPopup.findViewById(R.id.nomeResponsavel);
                telefone = formPopup.findViewById(R.id.telefone);
                dtNasc = formPopup.findViewById(R.id.dtNasc);

                // Configura o botão de adicionar no formulário
                adicionar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listaCrianca.add(new Crianca(nomeCrianca.getText().toString(), nomeResponsavel.getText().toString(), telefone.getText().toString(), dtNasc.getText().toString()));
                        myAdapter.notifyDataSetChanged();
                        db.collection("crianca").document("crianca_id").set(new Crianca(nomeCrianca.getText().toString(), nomeResponsavel.getText().toString(), telefone.getText().toString(), dtNasc.getText().toString()));
                        dialog.dismiss();
                    }
                });

                // Configura o botão de cancelar no formulário
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}