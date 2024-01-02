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

    private String mQuery;
    private int mPageNumber;
    private int movieId;

    public static MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;

    }
    public MovieRepository(){
      movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public LiveData<List<MovieModel>> getPop(){
        return movieApiClient.getMoviesPop();
    }

    public LiveData<List<MovieModel>> getTopRated(){
        return movieApiClient.getMoviesTopRated();
    }

    public void searchMovieApi(String query, int pageNumber){

        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);

    }
    public void searchMoviePop(int pageNumber){

        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPop(pageNumber);

    }
    public void searchMovieTopRated(int pageNumber){
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesTopRated(pageNumber);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery, mPageNumber+1);

    }

    public void fetchMovieDetails(int movieId, MovieRepository.OnMovieDetailsListener failedToFetchMovieDetails) {

        this.movieId = movieId;
    }

    public interface OnMovieDetailsListener {
        void onDetailsFetched(MovieModel movieDetails);
    }
}



