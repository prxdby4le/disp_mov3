package com.example.festajunina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editNome, editDescricao, editPreco;
    Button btnSalvar;
    BrincadeiraDAO dao;
    Brincadeira brincadeiraAtual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        editPreco = findViewById(R.id.editPreco);
        btnSalvar = findViewById(R.id.btnSalvar);

        dao = new BrincadeiraDAO(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("brincadeira")) {
            brincadeiraAtual = intent.getParcelableExtra("brincadeira");
            if (brincadeiraAtual != null && brincadeiraAtual.getId() > 0) {
                editNome.setText(brincadeiraAtual.getNome());
                editDescricao.setText(brincadeiraAtual.getDescricao());
                editPreco.setText(String.valueOf(brincadeiraAtual.getPreco()));
            } else {
                brincadeiraAtual = null;
            }
        }

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();
            String precoStr = editPreco.getText().toString().trim();

            if (nome.isEmpty() || precoStr.isEmpty()) {
                Toast.makeText(this, "Nome e Preço são obrigatórios", Toast.LENGTH_SHORT).show();
                return;
            }

            float preco;
            try {
                preco = Float.parseFloat(precoStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                if (brincadeiraAtual == null) {
                    Brincadeira nova = new Brincadeira();
                    nova.setNome(nome);
                    nova.setDescricao(descricao);
                    nova.setPreco(preco);

                    dao.inserir(nova);
                    Toast.makeText(this, "Brincadeira adicionada com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    brincadeiraAtual.setNome(nome);
                    brincadeiraAtual.setDescricao(descricao);
                    brincadeiraAtual.setPreco(preco);

                    dao.atualizar(brincadeiraAtual);
                    Toast.makeText(this, "Brincadeira atualizada com sucesso", Toast.LENGTH_SHORT).show();
                }

                finish(); // só finaliza se tudo ocorreu bem

            } catch (Exception e) {
                e.printStackTrace(); // mostra o erro no Logcat
                Toast.makeText(this, "Erro ao salvar: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        findViewById(R.id.btnListar).setOnClickListener(v -> {
            startActivity(new Intent(this, ListarBrincadeirasActivity.class));
        });
    }

    private void limparCampos() {
        editNome.setText("");
        editDescricao.setText("");
        editPreco.setText("");
    }
}
