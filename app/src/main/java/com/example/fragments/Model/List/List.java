package com.example.fragments.Model.List;

public class List {
    public String name;
    public int item_count;
    public int id;

    public List(String name, int item_count) {
        this.name = name;
        this.item_count = item_count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return item_count;
    }
}

