package com.newproject.tmdb;

import com.newproject.tmdb.models.MovieModel;

import java.util.List;

public interface MovieListView {

    void showProgress();
    void hideProgress();
    void diplayMovies(List<MovieModel> movies);
    void showError(String message);
    void navigateToMovieDetails(MovieModel movie);

    void displayMovies(List<MovieModel> popularMovies);
}
