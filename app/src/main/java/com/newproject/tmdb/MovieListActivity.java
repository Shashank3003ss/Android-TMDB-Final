package com.newproject.tmdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.newproject.tmdb.adapters.MovieRecyclerView;
import com.newproject.tmdb.adapters.OnMovieListener;
import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.request.Service;
import com.newproject.tmdb.response.MovieSearchResponse;
import com.newproject.tmdb.utils.Credentials;
import com.newproject.tmdb.utils.MovieApi;
import com.newproject.tmdb.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerViewAdapter;

//    ViewModel
    private MovieListViewModel movieListViewModel;

    boolean isPopular = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        SetupSearchView();
        ConfigureRecyclerView();
        ObserveAnyChange();

        ObservePopularMovies();

        movieListViewModel.searchMoviePop(1);

//        searchMovieApi("war",1);

    }

    private void ObservePopularMovies() {
        movieListViewModel.getPop().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        Log.v("Tag", "Name: " + movieModel.getTitle());
                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    //Observing any data change
    private void ObserveAnyChange(){

        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        Log.v("Tag", "Name: " + movieModel.getTitle());
                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

//    private void searchMovieApi(String query, int pageNumber){
//        movieListViewModel.searchMovieApi(query, pageNumber);
//    }

    private void ConfigureRecyclerView(){
        movieRecyclerViewAdapter =  new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Loading next pages
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)){
                    // Display the next results from the API
                    movieListViewModel.searchNextPage();
                }
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
//        Toast.makeText(this, "The position " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movieRecyclerViewAdapter.getSelectedMovie(position));
        startActivity(intent);

    }

    @Override
    public void onCategoryClick(String category) {

    }

    private void SetupSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular = false;
            }
        });

    }
}
//    private void GetRetrofitResponse() {
//        MovieApi movieApi = Service.getMovieApi();
//
//        Call<MovieSearchResponse> responseCall = movieApi
//                .searchMovie(
//                        Credentials.API_KEY,
//                        "Action",
//                        "1");
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                if (response.code() == 200 ){
//                    Log.v("Tag", "the response" + response.body().toString());
//
//                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
//
//                    for (MovieModel movie: movies){
//                        Log.v("Tag", "The release date" + movie.getRelease_date());
//
//                    }
//                }
//                else {
//                    try {
//                        Log.v("Tag", "Error" + response.errorBody().string());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//
//
//
//    }
//
//    private void GetRetrofitResponseAccordingToID(){
//        MovieApi movieApi = Service.getMovieApi();
//        Call<MovieModel> responseCall = movieApi
//                .getMovie(
//                        343611,
//                        Credentials.API_KEY);
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//
//
//                if(response.code() == 200){
//                    MovieModel movie = response.body();
//                    Log.v("Tag", "The Response " + movie.getTitle());
//                }
//
//                else{
//                    try {
//                        Log.v("Tag", "error" + response.errorBody().string());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//    }

