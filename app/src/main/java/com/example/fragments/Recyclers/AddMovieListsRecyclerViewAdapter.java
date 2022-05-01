package com.example.fragments.Recyclers;


import static com.example.fragments.Config.DefaultConstants.API_KEY;
import static com.example.fragments.Config.DefaultConstants.BASE_IMG_URL;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Config.GlideApp;
import com.example.fragments.DetailFragment;
import com.example.fragments.Model.Film.Film;
import com.example.fragments.Model.List.List;
import com.example.fragments.Model.List.ListDetail;
import com.example.fragments.Model.List.ListMovies;
import com.example.fragments.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieListsRecyclerViewAdapter extends RecyclerView.Adapter<AddMovieListsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<List> arrayList;
    private Context context;
    private ArrayList<ListMovies> arrayMovies;
    private int media_id;
    RecyclerView recyclerView;
    public AddMovieListsRecyclerViewAdapter(ArrayList<List> arrN, Context c){
        this.arrayList = arrN;
        this.context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.listTitle.setText(arrayList.get(i).getName());
        holder.itemCount.setText(String.valueOf(arrayList.get(i).getCount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View alertCustomdialog = inflater.inflate( R.layout.form_movie_to_list, null);

                TextView title = alertCustomdialog.findViewById(R.id.sectionTitle);
                title.setText("List movies");

                //initialize alert builder.
                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                //set our custom alert dialog to tha alertdialog builder
                alert.setView(alertCustomdialog);

                final AlertDialog dialog = alert.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                recyclerView = alertCustomdialog.findViewById(R.id.recyclerList);

                ApiCall apiCall = retrofit.create(ApiCall.class);
                Log.i("id", "" + arrayList.get(i).getId());
                Call<ListDetail> call = apiCall.getMoviesOfList(String.valueOf(arrayList.get(i).getId()), API_KEY);

                call.enqueue(new Callback<ListDetail>(){
                    @Override
                    public void onResponse(Call<ListDetail> call, Response<ListDetail> response) {
                        if(response.code()!=200){
                            Log.i("ListFragment", "error");
                            return;
                        }else {
                            Log.i("ListFragment", "good");
                            arrayMovies = response.body().getItems();
                            callRecyclerMovies(arrayMovies);
                            Log.i("ListFragment results", "length: " + arrayList.size());
                        }
                    }

                    @Override
                    public void onFailure(Call<ListDetail> call, Throwable t) {
                        Log.i("MoviesListFragment", "error" + t.getMessage());
                    }
                });
            }
        });


    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView listTitle;
    TextView itemCount;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        listTitle = itemView.findViewById(R.id.listTitle);
        itemCount= itemView.findViewById(R.id.itemCount);
    }
}

    public void callRecyclerMovies(ArrayList<ListMovies> arrayMovies){
        MoviesOfListRecyclerViewAdapter adapter = new MoviesOfListRecyclerViewAdapter(arrayMovies, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}

