package com.newproject.tmdb;

import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.repositories.MovieRepository;

public class MovieDetailsModel implements MovieDetailContract.Model {
    // Assuming there's a method to fetch movie details from some data source
    private MovieRepository movieRepository; // or any data source you're using

    public MovieDetailsModel() {
        // Initialize your movie repository or data source
        this.movieRepository = new MovieRepository(); // Instantiate your repository or data source
    }

    @Override
    public void getMovieDetails(int movieId, OnFinishedListener listener) {
        // Fetch movie details asynchronously from the repository or data source
        movieRepository.fetchMovieDetails(movieId, new MovieRepository.OnMovieDetailsListener() {
            @Override
            public void onDetailsFetched(MovieModel movieDetails) {
                if (movieDetails != null) {
                    // If movieDetails is not null, notify the listener with the fetched details
                    listener.onMovieDetailsFetched(movieDetails);
                } else {
                    // If there's an error, notify the listener with an error message
                    listener.onError("Failed to fetch movie details");
                }
            }
        });
    }
}
