package com.example.festajunina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListarBrincadeirasFragment extends Fragment {
    private RecyclerView recyclerView;
    private BrincadeiraAdapter adapter;
    private List<Brincadeira> lista;
    private BrincadeiraDAO dao;
    private Button btnNova;

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
    public void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        lista = dao.listar();
        adapter = new BrincadeiraAdapter(getContext(), lista, brincadeira -> {
            // Navegação para visualização detalhada
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
}
