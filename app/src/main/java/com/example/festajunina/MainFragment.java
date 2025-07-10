package com.example.festajunina;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    private EditText editNome, editDescricao, editPreco;
    private Button btnSalvar, btnListar;
    private ImageView imgPreview;
    private BrincadeiraDAO dao;
    private Brincadeira brincadeiraAtual = null;
    private static final int REQUEST_IMAGE_PICK = 1001;
    private String imageUriSelecionada = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        editNome = view.findViewById(R.id.editNome);
        editDescricao = view.findViewById(R.id.editDescricao);
        editPreco = view.findViewById(R.id.editPreco);
        btnSalvar = view.findViewById(R.id.btnSalvar);
        btnListar = view.findViewById(R.id.btnListar);
        imgPreview = view.findViewById(R.id.imgPreview);
        dao = new BrincadeiraDAO(getContext());

        // Preenche campos se vier brincadeira para edição
        if (getArguments() != null && getArguments().containsKey("brincadeira")) {
            brincadeiraAtual = getArguments().getParcelable("brincadeira");
            if (brincadeiraAtual != null) {
                editNome.setText(brincadeiraAtual.getNome());
                editDescricao.setText(brincadeiraAtual.getDescricao());
                editPreco.setText(String.valueOf(brincadeiraAtual.getPreco()));
                // Sempre exibe a imagem padrão
                imgPreview.setImageResource(R.drawable.comidas_tipicas_para_festa_junina);
            }
        }

        // Sempre exibe a imagem padrão
        imgPreview.setImageResource(R.drawable.comidas_tipicas_para_festa_junina);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString();
            String descricao = editDescricao.getText().toString();
            String precoStr = editPreco.getText().toString();
            if (nome.isEmpty() || descricao.isEmpty() || precoStr.isEmpty()) {
                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }
            float preco = Float.parseFloat(precoStr);
            if (brincadeiraAtual == null) {
                Brincadeira nova = new Brincadeira(0, nome, descricao, preco, null);
                dao.inserir(nova);
                Toast.makeText(getContext(), "Brincadeira salva!", Toast.LENGTH_SHORT).show();
            } else {
                brincadeiraAtual.setNome(nome);
                brincadeiraAtual.setDescricao(descricao);
                brincadeiraAtual.setPreco(preco);
                // Não altera imagem
                dao.atualizar(brincadeiraAtual);
                Toast.makeText(getContext(), "Brincadeira atualizada!", Toast.LENGTH_SHORT).show();
            }
            limparCampos();
        });

        btnListar.setOnClickListener(v -> {
            // Troca para o fragment de listagem
            if (getActivity() instanceof FragmentNavigation) {
                ((FragmentNavigation) getActivity()).navigateToListarBrincadeiras();
            }
        });

        return view;
    }

    private void limparCampos() {
        editNome.setText("");
        editDescricao.setText("");
        editPreco.setText("");
        imgPreview.setImageResource(R.drawable.comidas_tipicas_para_festa_junina);
        brincadeiraAtual = null;
        imageUriSelecionada = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Não faz nada, pois não há mais upload de imagem
    }
}
