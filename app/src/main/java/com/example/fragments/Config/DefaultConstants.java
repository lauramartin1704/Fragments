package com.example.fragments.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultConstants {

    public static final String API_KEY = "1f186d1a0c64bb9c62ed59cf97f24838";
    public static final String SESSION_ID = "7e25f1c0ca85b479addd1a7e6c453cb4f3ff9fe4";
    public static final String ACCOUNT_ID = "lmartin";

    public static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/";

    public static final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.themoviedb.org/3/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

}
