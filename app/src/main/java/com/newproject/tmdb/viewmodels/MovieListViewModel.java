package com.newproject.tmdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;


//    Constructor
    public MovieListViewModel(){
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getPop(){
        return movieRepository.getPop();
    }

    public LiveData<List<MovieModel>> getTopRated(){
        return movieRepository.getTopRated();
    }

    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchMoviePop(int pageNumber){
        movieRepository.searchMoviePop(pageNumber);
    }

    public void searchMovieTopRated(int pageNumber){
        movieRepository.searchMovieTopRated(pageNumber);
    }

    public void searchNextPage(){

        movieRepository.searchNextPage();
    }

}
