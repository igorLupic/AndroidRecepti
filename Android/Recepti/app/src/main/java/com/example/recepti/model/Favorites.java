package com.example.recepti.model;

public class Favorites {


    private int id;
    private int recepti_id;

    public Favorites(int id, int article_id) {
        this.id = id;
        this.recepti_id = recepti_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecepti_id() {
        return recepti_id;
    }

    public void setRecepti_id(int article_id) {
        this.recepti_id = recepti_id;
    }
}
