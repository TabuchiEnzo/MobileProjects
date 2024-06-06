package com.example.appbuffetfirebase;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCrianca extends RecyclerView.Adapter<AdapterCrianca.ViewHolder> {
    private List<Crianca> criancaList;

    DatabaseCrianca databaseCrianca = new DatabaseCrianca();

    public AdapterCrianca(List<Crianca> arg) {
        this.criancaList = arg;
    }

    @NonNull
    @Override
    public AdapterCrianca.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_crianca, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // pegando os dados da lista e adicionando no recycle view
        holder.nomeCrianca.setText(criancaList.get(position).getNomeCrianca());
        holder.nomeResponsavel.setText(criancaList.get(position).getNomeResponsavel());
        holder.telefone.setText(criancaList.get(position).getTelefone());
        holder.dtNasc.setText(criancaList.get(position).getDtNasc());
    }

    // Adicionando o SimpleCallback para o ItemTouchHelper
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false; // Não estamos implementando arrastar e soltar
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            // Quando o item for deslizado, você pode executar ações como remover o item do adapter
            int position = viewHolder.getAdapterPosition();
            Crianca crianca = criancaList.get(position);
            removeItem(position);
            databaseCrianca.deletar(crianca, viewHolder.itemView.getContext());
            Toast.makeText(viewHolder.itemView.getContext(), "Removido", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public int getItemCount() {
        return criancaList.size();
    }

    public void removeItem(int position) {
        criancaList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomeCrianca, nomeResponsavel, telefone, dtNasc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCrianca = itemView.findViewById(R.id.nomeCrianca);
            nomeResponsavel = itemView.findViewById(R.id.nomeResponsavel);
            telefone = itemView.findViewById(R.id.telefone);
            dtNasc = itemView.findViewById(R.id.dtNasc);
        }
    }
}
