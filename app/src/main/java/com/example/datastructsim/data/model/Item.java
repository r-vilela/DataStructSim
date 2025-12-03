package com.example.datastructsim.data.model;

public class Item {
    private int id;
    private String titulo;
    private boolean concluido;

    public Item(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.concluido = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isConcluido(){
        return concluido;
    }

    public void setConcluido(boolean concluido){
        this.concluido = concluido;
    }
}
