package com.example.projethsp.Entity;

public class Chambre {
    private int id;
    private String num;
    private int ref_status;

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

    public int getRef_status() {
        return ref_status;
    }

    public void setRef_status(int ref_status) {
        this.ref_status = ref_status;
    }

    public Chambre(int id, String num, int ref_status) {
        this.id = id;
        this.num = num;
        this.ref_status = ref_status;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", num='" + num + '\'' +
                ", ref_status=" + ref_status;
    }
}
