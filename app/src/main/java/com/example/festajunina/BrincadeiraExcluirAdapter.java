package com.example.festajunina;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BrincadeiraExcluirAdapter extends RecyclerView.Adapter<BrincadeiraExcluirAdapter.ViewHolder> {
    public interface OnExcluirClickListener {
        void onExcluir(Brincadeira brincadeira);
    }
    private List<Brincadeira> lista;
    private OnExcluirClickListener listener;

    public BrincadeiraExcluirAdapter(List<Brincadeira> lista, OnExcluirClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brincadeira_excluir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brincadeira brincadeira = lista.get(position);
        holder.txtNome.setText(brincadeira.getNome());
        holder.txtDescricao.setText(brincadeira.getDescricao());
        holder.txtPreco.setText(String.valueOf(brincadeira.getPreco()));
        if (brincadeira.getImageUri() != null && !brincadeira.getImageUri().isEmpty()) {
            holder.imgPreview.setImageURI(Uri.parse(brincadeira.getImageUri()));
        } else {
            holder.imgPreview.setImageResource(android.R.color.darker_gray);
        }
        holder.btnExcluir.setOnClickListener(v -> listener.onExcluir(brincadeira));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtDescricao, txtPreco;
        ImageView imgPreview;
        Button btnExcluir;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtPreco = itemView.findViewById(R.id.txtPreco);
            imgPreview = itemView.findViewById(R.id.imgPreview);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}
