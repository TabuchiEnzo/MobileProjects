package com.example.appbuffetfirebase;

import android.content.Context;
import android.media.MediaDrm;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class DatabaseCrianca {
    public DatabaseCrianca() {}

    public void salvar(Crianca crianca) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("crianca").add(crianca).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    DocumentReference documentReference = task.getResult();
                    crianca.setId(Integer.parseInt(documentReference.getId()));
                    db.collection("crianca").document(documentReference.getId()).set(crianca);
                }
            }
        });
    }

    public void deletar(Crianca crianca, Context contexto) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("crianca").document(String.valueOf(crianca.getId())).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(contexto, "Removido com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void listar(List<Crianca> criancaList, AdapterCrianca adapterCrianca) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("crianca").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                criancaList.clear();
                adapterCrianca.notifyDataSetChanged();
                for (DocumentSnapshot document : value.getDocuments()) {
                    Crianca crianca = document.toObject(Crianca.class);
                    criancaList.add(crianca);
                    adapterCrianca.notifyItemInserted(criancaList.size());
                }
            }
        });
    }

}
