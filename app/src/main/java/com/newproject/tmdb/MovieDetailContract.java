package com.newproject.tmdb;

import com.newproject.tmdb.models.MovieModel;

public interface MovieDetailContract {

    interface View {
        void displayMovieDetails(MovieModel movie);
        void showError(String message);

        default void showProgress() {
            
        }

        void hideProgress();
        // Add other view-related methods as needed
    }

    interface Presenter {
        void getMovieDetails(int movieId);
        void onDestroy();
    }

    interface Model {
        interface OnFinishedListener {
            void onMovieDetailsFetched(MovieModel movie);
            void onError(String errorMessage);
        }

        void getMovieDetails(int movieId, OnFinishedListener listener);
    }
}
