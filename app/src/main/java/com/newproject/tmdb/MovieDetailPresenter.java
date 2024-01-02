package com.newproject.tmdb;

import com.newproject.tmdb.models.MovieModel;

public class MovieDetailPresenter implements MovieDetailContract.Presenter, MovieDetailContract.Model.OnFinishedListener {

    private MovieDetailContract.View view;
    private MovieDetailContract.Model model;

    public MovieDetailPresenter(MovieDetailContract.View view, MovieDetailContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getMovieDetails(int movieId) {
        view.showProgress(); // Show loading indicator if needed

        model.getMovieDetails(movieId, this);
    }

    @Override
    public void onMovieDetailsFetched(MovieModel movie) {
        view.displayMovieDetails(movie);
        view.hideProgress(); // Hide loading indicator after fetching data
    }

    @Override
    public void onError(String errorMessage) {
        view.showError(errorMessage);
        view.hideProgress(); // Hide loading indicator in case of an error
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}

