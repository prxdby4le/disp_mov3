package com.example.festajunina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListarBrincadeirasFragment extends Fragment {
    private RecyclerView recyclerView;
    private BrincadeiraAdapter adapter;
    private List<Brincadeira> lista;
    private BrincadeiraDAO dao;
    private Button btnNova;
    private boolean ordemAscendente = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Habilita o menu no Fragment
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_brincadeiras, container, false);
        btnNova = view.findViewById(R.id.btnNovaBrincadeira);
        recyclerView = view.findViewById(R.id.recyclerBrincadeiras);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dao = new BrincadeiraDAO(getContext());
        carregarLista();
        btnNova.setOnClickListener(v -> {
            if (getActivity() instanceof FragmentNavigation) {
                ((FragmentNavigation) getActivity()).navigateToMainFragment();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_listar_brincadeira, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_ordenar) {
            ordenarListaAlfabeticamente();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        lista = dao.listar();
        ordenarListaAlfabeticamente(); // Ordena a lista ao carregar
        adapter = new BrincadeiraAdapter(getContext(), lista, brincadeira -> {
            if (getActivity() instanceof FragmentNavigation) {
                Bundle args = new Bundle();
                args.putParcelable("brincadeira", brincadeira);
                VisualizarBrincadeiraFragment fragment = new VisualizarBrincadeiraFragment();
                fragment.setArguments(args);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void ordenarListaAlfabeticamente() {
        if (lista != null) {
            Collections.sort(lista, new Comparator<Brincadeira>() {
                @Override
                public int compare(Brincadeira b1, Brincadeira b2) {
                    int comparacao = b1.getNome().compareToIgnoreCase(b2.getNome());
                    return ordemAscendente ? comparacao : -comparacao;
                }
            });
            ordemAscendente = !ordemAscendente; // Alterna a ordem para o próximo clique
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}