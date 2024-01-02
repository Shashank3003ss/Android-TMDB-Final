package com.newproject.tmdb;

import com.newproject.tmdb.models.MovieModel;

import java.util.List;

public class MovieListPresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

    private MovieListContract.View view;
    private MovieListContract.Model model;

    public MovieListPresenter(MovieListContract.View view, MovieListContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getPopularMovies(int page) {
        view.showProgress();
        model.getPopularMovies(page, this);
    }

    @Override
    public void getTopRatedMovies(int page) {
        view.showProgress();
        model.getTopRatedMovies(page, this);
    }

    @Override
    public void searchMovies(String query, int page) {
        view.showProgress();
        model.searchMovies(query, page, this);
    }

    @Override
    public void onMoviesFetched(List<MovieModel> movies) {
        view.hideProgress();
        view.displayMovies(movies);
    }

    @Override
    public void onSearchMoviesFetched(List<MovieModel> movies) {
        view.hideProgress();
        view.displayMovies(movies);
    }

    @Override
    public void onError(String errorMessage) {
        view.hideProgress();
        view.showError(errorMessage);
    }

    @Override
    public void onMovieClick(int position) {
        // Assuming you have a method to get the selected movie from the adapter
        MovieModel selectedMovie = view.getSelectedMovie(position);
        view.navigateToMovieDetails(selectedMovie);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
