package com.example.festajunina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BrincadeiraAdapter extends RecyclerView.Adapter<BrincadeiraAdapter.ViewHolder> {

    private final Context context;
    private final List<Brincadeira> lista;
    private final OnBrincadeiraClickListener clickListener;

    public interface OnBrincadeiraClickListener {
        void onBrincadeiraClick(Brincadeira brincadeira);
    }

    public BrincadeiraAdapter(Context context, List<Brincadeira> lista, OnBrincadeiraClickListener clickListener) {
        this.context = context;
        this.lista = lista;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brincadeira_listar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brincadeira b = lista.get(position);
        holder.nome.setText(b.getNome());
        holder.descricao.setText(b.getDescricao());
        // Sempre exibe a imagem padrão
        holder.imgPreview.setImageResource(R.drawable.comidas_tipicas_para_festa_junina);
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onBrincadeiraClick(b);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome, descricao;
        ImageView imgPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txtNome);
            descricao = itemView.findViewById(R.id.txtDescricao);
            imgPreview = itemView.findViewById(R.id.imgPreview);
        }
    }
}