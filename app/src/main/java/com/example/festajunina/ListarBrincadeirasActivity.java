package com.example.festajunina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListarBrincadeirasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BrincadeiraAdapter adapter;
    List<Brincadeira> lista;
    BrincadeiraDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_brincadeiras);

        Button btnNova = findViewById(R.id.btnNovaBrincadeira);
        recyclerView = findViewById(R.id.recyclerBrincadeiras);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnNova.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        dao = new BrincadeiraDAO(this);
        carregarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        lista = dao.listar();
        adapter = new BrincadeiraAdapter(this, lista);
        recyclerView.setAdapter(adapter);
    }
}
