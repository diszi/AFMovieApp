package d2.hu.afmovieapp;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recyclerView)
    RecyclerView searchableRecyclerView;
    @BindView(R.id.searchField)
    SearchView searchView;
    @BindView(R.id.tool_bar)
    Toolbar compToolbar;


    private List<Movie> moviesList = new ArrayList();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        compToolbar.setTitle("Movies");
        loadMovies();
        this.setupRecyclerView();







        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return true;
            }
        });
    }


    private void setupRecyclerView() {

        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new MainAdapter();
        this.adapter.setMovies(moviesList,this);
        this.searchableRecyclerView.setLayoutManager(layoutManager);
        this.searchableRecyclerView.setAdapter(this.adapter);

    }



    public void loadMovies(){

        APIService api = RetroClient.getApiService();


        Call<Movie> call_movie = api.getMyJSON_MOVIE();

        call_movie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showItMovie(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });


    }


    private void showItMovie(Movie movie){

        moviesList = new ArrayList<>();
        moviesList.add(movie);

    }


    public void openMovieDetails(Movie movie){
        Intent intent  =  new Intent(this,DetailsActivity.class);

        startActivity(intent);
    }



}
