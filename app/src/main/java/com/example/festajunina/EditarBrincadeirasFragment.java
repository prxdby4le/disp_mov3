package com.example.festajunina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EditarBrincadeirasFragment extends Fragment {
    private RecyclerView recyclerView;
    private BrincadeiraEditarAdapter adapter;
    private List<Brincadeira> lista;
    private BrincadeiraDAO dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_brincadeiras, container, false);
        recyclerView = view.findViewById(R.id.recyclerEditarBrincadeiras);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dao = new BrincadeiraDAO(getContext());
        carregarLista();
        return view;
    }

    private void carregarLista() {
        lista = dao.listar();
        adapter = new BrincadeiraEditarAdapter(lista, brincadeira -> {
            if (getActivity() instanceof FragmentNavigation) {
                ((FragmentNavigation) getActivity()).navigateToEditarBrincadeira(brincadeira);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}

