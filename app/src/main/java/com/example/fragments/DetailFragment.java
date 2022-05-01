package com.example.fragments;

import static com.example.fragments.Config.DefaultConstants.API_KEY;
import static com.example.fragments.Config.DefaultConstants.BASE_IMG_URL;
import static com.example.fragments.Config.DefaultConstants.SESSION_ID;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Config.GlideApp;
import com.example.fragments.Model.Film.AcountStates;
import com.example.fragments.Model.Film.FavFilmRequest;
import com.example.fragments.Model.Film.FavFilmResponse;
import com.example.fragments.Model.Film.Film;
import com.example.fragments.Model.List.List;
import com.example.fragments.Model.List.ListResponse;
import com.example.fragments.Recyclers.AddMovieListsRecyclerViewAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {

    boolean favStatus;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        Film film = (Film) bundle.getSerializable("Film");

        TextView txtDetailTitle = view.findViewById(R.id.txtDetailTitle);
        TextView txtDetailDesc = view.findViewById(R.id.txtDetailDesc);
        ImageView imgDetail = view.findViewById(R.id.imgDetail);
        ImageButton btnFav = view.findViewById(R.id.btnFav);
        ImageButton btnAddtoList = view.findViewById(R.id.btnAddtoList);

        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<AcountStates> callFavStatus = apiCall.getFavStatus(film.getId(), API_KEY, SESSION_ID);
        callFavStatus.enqueue(new Callback<AcountStates>(){
            @Override
            public void onResponse(Call<AcountStates> call, Response<AcountStates> response) {
                if(response.code()!=200){
                    Log.i("testApi", "checkConnection");
                    return;
                }else {
                    Log.i("favTest", "Se ha obtenido el estado");
                    AcountStates filmStatus = new AcountStates(response.body().getId(), response.body().getFavorite());
                    if(filmStatus.getFavorite() == true){
                        btnFav.setImageResource(R.drawable.ic_fav_on);
                        favStatus=true;
                    } else {
                        btnFav.setImageResource(R.drawable.ic_fav_off);
                        favStatus=false;
                    }
                }
            }

            @Override
            public void onFailure(Call<AcountStates> call, Throwable t) {

            }
        });
        txtDetailTitle.setText(film.getOriginal_title());
        txtDetailDesc.setText(film.getOverview());

        GlideApp.with(getContext())
                .load(BASE_IMG_URL + film.getPoster_path())
                .centerCrop()
                .into(imgDetail);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavFilmRequest request;
                if(favStatus == true){
                btnFav.setImageResource(R.drawable.ic_fav_off);
                    request = new FavFilmRequest("movie", film.getId(), false);
                } else {
                    btnFav.setImageResource(R.drawable.ic_fav_on);
                    request = new FavFilmRequest("movie", film.getId(), true);
                }

                Call<FavFilmResponse> call = apiCall.setFav(API_KEY, SESSION_ID, request);

                call.enqueue(new Callback<FavFilmResponse>(){
                    @Override
                    public void onResponse(Call<FavFilmResponse> call, Response<FavFilmResponse> response) {
                        if(response.code()!=201){
                            Log.i("testApi", "checkConnection");
                            return;
                        }else {
                            Log.i("favTest", "guardado");
                        }
                    }
                    @Override
                    public void onFailure(Call<FavFilmResponse> call, Throwable t) {

                    }
                });

            }
        });

        btnAddtoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
               }
        });


        return view;

    }

    public void showDialog(){
        View alertCustomdialog = getLayoutInflater().inflate( R.layout.form_movie_to_list, null);

        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        //set our custom alert dialog to tha alertdialog builder
        alert.setView(alertCustomdialog);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();


        ArrayList<List> arrayList = new ArrayList<List>();
        arrayList.add(new List("Comedia", 8));
        arrayList.add(new List("Ciència", 8));
        arrayList.add(new List("Terror", 8));
        arrayList.add(new List("Comedia", 8));
        arrayList.add(new List("Ciència", 8));
        arrayList.add(new List("Terror", 8));
        arrayList.add(new List("Comedia", 8));
        arrayList.add(new List("Ciència", 8));
        arrayList.add(new List("Terror", 8));


        RecyclerView recyclerView = alertCustomdialog.findViewById(R.id.recyclerList);
        AddMovieListsRecyclerViewAdapter adapter = new AddMovieListsRecyclerViewAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}