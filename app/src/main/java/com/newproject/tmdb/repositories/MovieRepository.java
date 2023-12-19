package com.newproject.tmdb.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
//    This class is acting as repositories

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;

    }
    private MovieRepository(){
      movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public void searchMovieApi(String query, int pageNumber){
        movieApiClient.searchMoviesApi(query, pageNumber);
    }




}



