package com.example.projethsp.Entity;

public class Niveau {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Niveau(int id, int num) {
        this.id = id;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Niveau Gravité : "+ num ;
    }

    private int num;
}
