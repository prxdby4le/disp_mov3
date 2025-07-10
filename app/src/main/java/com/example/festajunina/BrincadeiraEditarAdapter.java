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

public class BrincadeiraEditarAdapter extends RecyclerView.Adapter<BrincadeiraEditarAdapter.ViewHolder> {
    public interface OnEditarClickListener {
        void onEditar(Brincadeira brincadeira);
    }
    private List<Brincadeira> lista;
    private OnEditarClickListener listener;

    public BrincadeiraEditarAdapter(List<Brincadeira> lista, OnEditarClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brincadeira_editar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brincadeira brincadeira = lista.get(position);
        holder.txtNome.setText(brincadeira.getNome());
        holder.txtDescricao.setText(brincadeira.getDescricao());
        holder.txtPreco.setText(String.valueOf(brincadeira.getPreco()));
        // Sempre exibe a imagem padrão
        holder.imgPreview.setImageResource(R.drawable.comidas_tipicas_para_festa_junina);
        holder.btnEditar.setOnClickListener(v -> listener.onEditar(brincadeira));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtDescricao, txtPreco;
        ImageView imgPreview;
        Button btnEditar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtPreco = itemView.findViewById(R.id.txtPreco);
            imgPreview = itemView.findViewById(R.id.imgPreview);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }
}
