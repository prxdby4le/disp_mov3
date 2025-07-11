package com.example.festajunina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BrincadeiraAdapter extends RecyclerView.Adapter<BrincadeiraAdapter.ViewHolder> {

    private final Context context;
    private List<Brincadeira> lista;
    private final OnBrincadeiraClickListener clickListener;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public interface OnBrincadeiraClickListener {
        void onBrincadeiraClick(Brincadeira brincadeira);
    }

    public void atualizarLista(List<Brincadeira> novaLista) {
        this.lista = novaLista;
        notifyDataSetChanged();
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

        // Formata o preço como moeda brasileira (R$)
        try {
            if (b.getPreco() > 0) {  // Assumindo que getPreco() retorna float
                holder.preco.setText(String.format(Locale.getDefault(), "R$ %.2f", b.getPreco()));
            } else {
                holder.preco.setText("R$ --,--");
            }
        } catch (Exception e) {
            holder.preco.setText("R$ --,--");
        }

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
        TextView nome, descricao, preco;
        ImageView imgPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txtNome);
            descricao = itemView.findViewById(R.id.txtDescricao);
            preco = itemView.findViewById(R.id.txtPreco);
            imgPreview = itemView.findViewById(R.id.imgPreview);
        }
    }
}