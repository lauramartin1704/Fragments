package com.example.fragments.Config;

import com.example.fragments.Model.Film.AcountStates;
import com.example.fragments.Model.Film.FavFilmRequest;
import com.example.fragments.Model.Film.FavFilmResponse;
import com.example.fragments.Model.Film.searchFilmModel;
import com.example.fragments.Model.List.ListAddItemRequest;
import com.example.fragments.Model.List.ListDetail;
import com.example.fragments.Model.List.ListModel;
import com.example.fragments.Model.List.ListRequest;
import com.example.fragments.Model.List.ListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("search/movie?")
    Call<searchFilmModel> getData(@Query("api_key") String api_key, @Query("query") String query);
    @GET("account/lmartin/favorite/movies?")
    Call<searchFilmModel> getFav(@Query("api_key") String api_key, @Query("session_id") String session_id);
    @GET("account/lmartin/lists?")
    Call<ListModel> getLists(@Query("api_key") String api_key, @Query("session_id") String session_id);

    @GET("list/{list_id}?")
    Call<ListDetail> getMoviesOfList(@Query("list_id") String list_id, @Query("api_key") String api_key);
    @POST("list?")
    Call<ListResponse> postList(@Query("api_key") String api_key, @Query("session_id") String session_id, @Body ListRequest request);

    @POST("list/{list_id}/add_item?")
    Call<ListResponse> addItem(@Query("list_id") int list_id, @Query("api_key") String api_key, @Query("session_id") String session_id, @Body ListAddItemRequest request);


    @GET("movie/{movie_id}/account_states?")
    Call<AcountStates> getFavStatus(@Path("movie_id") int movie_id,
                                    @Query("api_key") String api_key,
                                    @Query("session_id") String session_id);

    @POST("account/lmartin/favorite?")

    @Headers({"Content-Type: application/json;charset=utf-8"})
    Call<FavFilmResponse> setFav(@Query("api_key") String api_key,
                                 @Query("session_id") String session_id,
                                 @Body FavFilmRequest request);
}

