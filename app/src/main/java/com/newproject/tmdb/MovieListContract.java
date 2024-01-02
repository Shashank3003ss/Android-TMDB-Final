package com.newproject.tmdb;

import com.newproject.tmdb.models.MovieModel;

import java.util.List;

public interface MovieListContract {

    interface View {
        void showProgress();
        void hideProgress();
        void displayMovies(List<MovieModel> movies);
        void showError(String message);
        void navigateToMovieDetails(MovieModel movie);

        MovieModel getSelectedMovie(int position);
    }

    interface Presenter {
        void getPopularMovies(int page);
        void getTopRatedMovies(int page);
        void searchMovies(String query, int page);
        void onMovieClick(int position);
        void onDestroy();
    }

    interface Model {
        interface OnFinishedListener {
            void onMoviesFetched(List<MovieModel> movies);
            void onSearchMoviesFetched(List<MovieModel> movies);
            void onError(String errorMessage);
        }

        void getPopularMovies(int page, OnFinishedListener listener);
        void getTopRatedMovies(int page, OnFinishedListener listener);
        void searchMovies(String query, int page, OnFinishedListener listener);
    }
}

