package com.example.projethsp.Entity;

public class Chambre {
    private int id;
    private String num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Chambre(int id, String num) {
        this.id = id;
        this.num = num;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", num='" + num;
    }
}
