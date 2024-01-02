package com.newproject.tmdb;

public class MovieListModel implements MovieListContract.Model {

    @Override
    public void getPopularMovies(int page, OnFinishedListener listener) {
        // Implement the logic to fetch popular movies from your data source (API, Database, etc.)
        // Notify the listener when the data is available
    }

    @Override
    public void getTopRatedMovies(int page, OnFinishedListener listener) {
        // Implement the logic to fetch top-rated movies from your data source (API, Database, etc.)
        // Notify the listener when the data is available
    }

    @Override
    public void searchMovies(String query, int page, OnFinishedListener listener) {
        // Implement the logic to search movies based on the query from your data source (API, Database, etc.)
        // Notify the listener when the search results are available
    }
}
