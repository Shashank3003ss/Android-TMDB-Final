package com.newproject.tmdb.utils;

import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

//Search for movies
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );


//    https://api.themoviedb.org/3/movie/157336?api_key=9611ad116cf8e918fd77cfebc08a7c41

//    Get popular movies
    @GET("3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key")String api_key,
            @Query("page") int page
    );

    @GET("3/movie/top_rated")
    Call<MovieSearchResponse> getTopRated(
            @Query("api_key")String api_key,
            @Query("page") int page
    );


//    making search with id
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key")String api_key
            );



    Call<MovieSearchResponse> searchMovie(String apiKey, String action, String number);
}
