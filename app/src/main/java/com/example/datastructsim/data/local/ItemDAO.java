package com.example.datastructsim.data.local;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datastructsim.data.model.Item;

import java.util.ArrayList;

public class ItemDAO implements IItemDAO {
    private SQLiteDatabase leitura;
    private SQLiteDatabase escrita;
    private DbHelper dbHelper;

    public ItemDAO(Context context){
        dbHelper = new DbHelper(context);
        this.leitura = dbHelper.getReadableDatabase();
        this.escrita = dbHelper.getWritableDatabase();
    }
    @Override
    public long inserir(ContentValues cv) {
        long id = this.escrita.insert("item", null, cv);
        return id;
    }

    @Override
    public void atualizar(int id, String titulo) {
        ContentValues valor = new ContentValues();
        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        valor.put("titulo", titulo);
        this.escrita.update("item", valor, where, whereArgs);
    }

    @Override
    public void deletar(int id) {
        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        this.escrita.delete("item", where, whereArgs);
    }

    @SuppressLint("Range")
    @Override
    public ContentValues pesquisarPorId(int id) {
        ContentValues cv = new ContentValues();
        String sql = "SELECT * FROM item WHERE id = ?";
        String where [] = new String[]{String.valueOf(id)};
        Cursor cursor = this.leitura.rawQuery(sql,where);
        if(cursor.moveToFirst()){
            cv.put("id", cursor.getInt(cursor.getColumnIndex("id")));
            cv.put("titulo", cursor.getString(cursor.getColumnIndex("titulo")));
        }
        cursor.close();
        return cv;
    }

    @SuppressLint("Range")
    @Override
    public ArrayList<Item> listar() {
        ArrayList<Item> lista = new ArrayList<>();
        String sql = "SELECT * FROM item ORDER BY id DESC";
        Cursor cursor = this.leitura.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            Item item = new Item(id, titulo);
            lista.add(item);
        }
        cursor.close();
        return lista;
    }
}
