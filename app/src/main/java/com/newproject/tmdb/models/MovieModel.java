package com.newproject.tmdb.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {

//    Model class for movies
    private String title;
    private String backdrop_path;
    private String release_date;
    private String poster_path;

    private String original_language;

    @SerializedName("id")
    private int movie_id;

    @SerializedName("vote_average")
    private float vote_average;



    @SerializedName("vote_count")
    private int vote_count;


    @SerializedName("overview")
    private String movie_overview;

    @SerializedName("runtime")
    private int runtime;

//    Constructor
    public MovieModel(String title, String backdrop_path, String release_date, int movie_id, float vote_average, String movie_overview,
         String original_language, int runtime, int vote_count, String poster_path, int popularity) {
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
        this.vote_count = vote_count;
        this.runtime = runtime;
        this.original_language = original_language;
    }

//    Parcelable
    protected MovieModel(Parcel in) {
        title = in.readString();
        backdrop_path = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
        runtime = in.readInt();
        vote_count = in.readInt();
        original_language = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    //    Getters
    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public int getRuntime() {return runtime;}

    public String getOriginal_language() {
        return original_language;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeInt(runtime);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
        dest.writeInt(vote_count);
        dest.writeString(original_language);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", movie_id=" + movie_id +
                ", vote_count=" + vote_count +
                ", vote_average=" + vote_average +
                ", movie_overview='" + movie_overview + '\'' +
                ", runtime=" + runtime +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
