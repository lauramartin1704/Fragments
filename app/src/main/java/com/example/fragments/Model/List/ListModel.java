package com.example.fragments.Model.List;

import java.util.ArrayList;

public class ListModel {
    public String name;
    public String description;
    public String language;
    public int page;
    public ArrayList<List> results;
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getPage() {
            return page;
        }

    public ArrayList<List> getResults() {
            return results;
        }


}
