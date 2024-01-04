package com.newproject.tmdb;

import com.newproject.tmdb.models.MovieModel;

import java.util.List;

public class MovieListPresenter {

    private MovieListView view;
    private MovieListModel model;

    public MovieListPresenter(MovieListView view, MovieListModel model){
        this.view = view;
        this.model = model;
    }

    public void getPopularMovies(int page){
        view.showProgress();
        List<MovieModel> popularMovies = model.fetchPopularMovies(page);
        if (popularMovies != null && !popularMovies.isEmpty()){
            view.displayMovies(popularMovies);
        }
        else {
            view.showError("Failed to fetch popular movies");
        }
        view.hideProgress();
    }

    public void getTopRatedMovies(int page){
        view.showProgress();
        List<MovieModel> topRatedMovies = model.fetchTopRatedMovies(page);
        if (topRatedMovies != null && !topRatedMovies.isEmpty()){
            view.displayMovies(topRatedMovies);
        }
        else {
            view.showError("Failed to fetch top rated movies");
        }
        view.hideProgress();
    }
}
