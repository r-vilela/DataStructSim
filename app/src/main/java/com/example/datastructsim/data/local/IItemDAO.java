package com.example.datastructsim.data.local;

import android.content.ContentValues;


import com.example.datastructsim.data.model.Item;

import java.util.ArrayList;

public interface IItemDAO {
    public long inserir(ContentValues cv);
    public void atualizar(int id, String titulo);
    public void deletar(int id);
    public ContentValues pesquisarPorId(int id);
    public ArrayList<Item> listar();
}
