package com.example.festajunina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExcluirBrincadeirasFragment extends Fragment {
    private RecyclerView recyclerView;
    private BrincadeiraExcluirAdapter adapter;
    private List<Brincadeira> lista;
    private BrincadeiraDAO dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_excluir_brincadeiras, container, false);
        recyclerView = view.findViewById(R.id.recyclerExcluirBrincadeiras);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dao = new BrincadeiraDAO(getContext());
        carregarLista();
        return view;
    }

    private void carregarLista() {
        lista = dao.listar();
        adapter = new BrincadeiraExcluirAdapter(lista, brincadeira -> {
            new AlertDialog.Builder(requireContext())
                .setTitle("Confirmar exclusão")
                .setMessage("Deseja realmente excluir esta brincadeira?")
                .setPositiveButton("Excluir", (dialog, which) -> {
                    dao.excluir(brincadeira.getId());
                    Toast.makeText(getContext(), "Brincadeira excluída!", Toast.LENGTH_SHORT).show();
                    carregarLista();
                })
                .setNegativeButton("Cancelar", null)
                .show();
        });
        recyclerView.setAdapter(adapter);
    }
}
