package com.example.apprecycleview;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseSalaF {
    // pegar o id da nota
    Map<String, Object> nota_id = new HashMap<>();

    public DatabaseSalaF() {}

    public void salvar(Nota nota) {
        // abrir o firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("counters").document("nota_id").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                int id = 1;
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        nota_id = document.getData();
                        id = Integer.parseInt(nota_id.get("chave").toString()) + 1;

                    }
                    nota_id.put("chave", id);
                    db.collection("counters").document("nota_id").set(nota_id);

                    // gravando a nota
                    nota.setId(id);
                    db.collection("notas").document(String.valueOf(id)).set(nota);
                }
            }
        });
    }

    public void remover (Nota nota, Context contexto)   {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("notas").document(String.valueOf(nota.getId())).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(contexto, "Removido com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void listar (List<Nota> listaNota, AdapterNota adapterNota) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // recuperar dados em tempo real
        db.collection("notas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                listaNota.clear();
                adapterNota.notifyDataSetChanged();
                for (DocumentSnapshot document : value.getDocuments()) {
                    Nota nota = document.toObject(Nota.class);
                    listaNota.add(nota);
                    adapterNota.notifyItemInserted(listaNota.size());
                }
            }
        });
    }
}
