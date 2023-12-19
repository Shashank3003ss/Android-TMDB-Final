package com.newproject.tmdb.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.newproject.tmdb.models.MovieModel;

// this class is for single movie request
public class MovieResponse {

//  Finding the Movie Object
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
