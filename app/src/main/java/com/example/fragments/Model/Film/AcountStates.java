package com.example.fragments.Model.Film;


public class AcountStates {
    int id;
    boolean favorite;

    public AcountStates(int id, boolean favorite) {
        this.id = id;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
