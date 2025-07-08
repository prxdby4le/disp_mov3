package com.example.festajunina;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BrincadeiraAdapter extends RecyclerView.Adapter<BrincadeiraAdapter.ViewHolder> {

    private final Context context;
    private final List<Brincadeira> lista;
    private final BrincadeiraDAO dao;

    public BrincadeiraAdapter(Context context, List<Brincadeira> lista) {
        this.context = context;
        this.lista = lista;
        this.dao = new BrincadeiraDAO(context); // DAO usado para excluir
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_item_brincadeira, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brincadeira b = lista.get(position);
        holder.nome.setText(b.getNome());
        holder.descricao.setText(b.getDescricao());
        holder.preco.setText(String.format("R$ %.2f", b.getPreco()));

        // Clique no botão editar
        holder.btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("brincadeira", b);
            context.startActivity(intent);
        });

        // Clique no botão excluir
        holder.btnExcluir.setOnClickListener(v -> {
            dao.excluir(b.getId()); // Remove do banco de dados
            lista.remove(position); // Remove da lista
            notifyItemRemoved(position); // Atualiza RecyclerView
            notifyItemRangeChanged(position, lista.size());
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome, descricao, preco;
        Button btnEditar, btnExcluir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textNome);
            descricao = itemView.findViewById(R.id.textDescricao);
            preco = itemView.findViewById(R.id.textPreco);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}