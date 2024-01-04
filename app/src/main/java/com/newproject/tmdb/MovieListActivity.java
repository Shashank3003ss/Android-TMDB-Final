package com.newproject.tmdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AbsActionBarView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.newproject.tmdb.adapters.MovieRecyclerView;
import com.newproject.tmdb.adapters.OnMovieListener;
import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.viewmodels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieListView, OnMovieListener {

    private MovieListPresenter presenter;

    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerViewAdapter;
    private BottomNavigationItemView bottomNavigationItemView1, bottomNavigationItemView2;

    private ProgressBar progressBar;
//    ViewModel
    private MovieListViewModel movieListViewModel;
    boolean isPopular = true;
    boolean isTopRated = true;
    private int nextPageForPop = 1;
    private int nextPageForTopRated = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        presenter = new MovieListPresenter(this, new MovieListModel());
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        bottomNavigationItemView1 = findViewById(R.id.action_popular);
        bottomNavigationItemView2 = findViewById(R.id.action_topRated);
        bottomNavigationItemView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObservePopularMovies();
            }
        });
        bottomNavigationItemView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObserveTopRatedMovies();
            }
        });

        progressBar = findViewById(R.id.progressBar);
        presenter.getPopularMovies(1);
        presenter.getTopRatedMovies(1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SetupSearchView();
        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopularMovies();

        movieListViewModel.searchMoviePop(1);
        movieListViewModel.searchMovieTopRated(1);
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

    private void ObserveTopRatedMovies() {
        movieListViewModel.getTopRated().observe(this, new Observer<List<MovieModel>>() {
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

    private void ConfigureRecyclerView(){
        movieRecyclerViewAdapter =  new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        // Loading next pages
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                if (lastVisible + 1 == totalItemCount) {
                    if (isPopular) {
                        movieListViewModel.searchMoviePop(nextPageForPop);
                        nextPageForPop++;
                    } else if (isTopRated) {
                        movieListViewModel.searchMovieTopRated(nextPageForTopRated);
                        nextPageForTopRated++;
                    }
                }
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
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
                isTopRated = false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular = false;
            }
        });

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void diplayMovies(List<MovieModel> movies) {
        movieRecyclerViewAdapter.setmMovies(movies);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void navigateToMovieDetails(MovieModel movie) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public void displayMovies(List<MovieModel> popularMovies) {

    }
}


