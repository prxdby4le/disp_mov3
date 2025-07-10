package com.example.festajunina;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VisualizarBrincadeiraFragment extends Fragment {
    public static final String ARG_BRINCADEIRA = "brincadeira";
    private Brincadeira brincadeira;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualizar_brincadeira, container, false);
        TextView txtTitulo = view.findViewById(R.id.txtTituloBrincadeira);
        ImageView imgBrincadeira = view.findViewById(R.id.imgBrincadeira);
        TextView txtDescricao = view.findViewById(R.id.txtDescricaoBrincadeira);

        if (getArguments() != null && getArguments().containsKey(ARG_BRINCADEIRA)) {
            brincadeira = getArguments().getParcelable(ARG_BRINCADEIRA);
            if (brincadeira != null) {
                txtTitulo.setText(brincadeira.getNome());
                txtDescricao.setText(brincadeira.getDescricao());
                if (brincadeira.getImageUri() != null && !brincadeira.getImageUri().isEmpty()) {
                    imgBrincadeira.setImageURI(Uri.parse(brincadeira.getImageUri()));
                } else {
                    imgBrincadeira.setImageResource(android.R.color.darker_gray);
                }
            }
        }
        return view;
    }
}
