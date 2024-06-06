package com.example.apprecycleview;

import android.annotation.SuppressLint;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNota extends RecyclerView.Adapter<AdapterNota .MeuViewHolder> {

    private List<Nota> listaNota;
    DatabaseSalaF databaseSalaF = new DatabaseSalaF();

    public AdapterNota(List<Nota> arg) {
        this.listaNota = arg;
    }

    @NonNull
    @Override
    public AdapterNota.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // carregar o template de visualização
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota, parent, false);

        // chamar o ViewHolder para carregar os objetos
        return new MeuViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, @SuppressLint("RecyclerView") int position)     {
        // carregar os dados nos objetos
        holder.titulo.setText(listaNota.get(position).getTitulo());
        holder.descricao.setText(listaNota.get(position).getDescricao());

        // Personalizar
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        }

        // se necessario fazer alguma coisa personalizada aqui
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fazer alguma para aparecer uma tela para alterar aquele itemView que foi clicado
                Toast.makeText(holder.itemView.getContext(), "Clicado para alterar", Toast.LENGTH_SHORT).show();
            }
        });

        // fazer com que o item seja removido ao clicar longo
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                databaseSalaF.remover(listaNota.get(holder.getAdapterPosition()), v.getContext());
                Toast.makeText(holder.itemView.getContext(), "Removido", Toast.LENGTH_SHORT).show();

                listaNota.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaNota.size();
    }

    // Fazer o InnerClass para carregar os elementos XML vs JAVA
    public class MeuViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descricao;
        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            descricao = itemView.findViewById(R.id.descricao);
        }
    }
}