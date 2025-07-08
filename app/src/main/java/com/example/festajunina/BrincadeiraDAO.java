package com.example.festajunina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BrincadeiraDAO {
    private final SQLiteDatabase banco;

    public BrincadeiraDAO(Context context) {
        BancoHelper helper = new BancoHelper(context);
        banco = helper.getWritableDatabase();
    }

    public long inserir(Brincadeira b) {
        ContentValues valores = new ContentValues();
        valores.put("nome", b.getNome());
        valores.put("descricao", b.getDescricao());
        valores.put("preco", b.getPreco());
        return banco.insert("brincadeira", null, valores);
    }

    public void atualizar(Brincadeira b) {
        if (b.getId() <= 0) return; // segurança extra

        ContentValues values = new ContentValues();
        values.put("nome", b.getNome());
        values.put("descricao", b.getDescricao());
        values.put("preco", b.getPreco());

        banco.update("brincadeira", values, "id = ?", new String[]{String.valueOf(b.getId())});
    }

    public int excluir(int id) {
        return banco.delete("brincadeira", "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Brincadeira> listar() {
        List<Brincadeira> lista = new ArrayList<>();
        Cursor cursor = banco.query("brincadeira", new String[]{"id", "nome", "descricao", "preco"},
                null, null, null, null, "id DESC");

        while (cursor.moveToNext()) {
            Brincadeira b = new Brincadeira();
            b.setId(cursor.getInt(0));
            b.setNome(cursor.getString(1));
            b.setDescricao(cursor.getString(2));
            b.setPreco(cursor.getFloat(3));
            lista.add(b);
        }
        cursor.close();
        return lista;
    }
}
