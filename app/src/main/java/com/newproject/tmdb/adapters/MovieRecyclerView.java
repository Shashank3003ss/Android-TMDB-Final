package com.newproject.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newproject.tmdb.R;
import com.newproject.tmdb.models.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,
                parent,false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder){
            MovieModel currentMovie = mMovies.get(position);
            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;

            if (currentMovie != null) {
                // Set movie title
                if (movieViewHolder.title != null) {
                    movieViewHolder.title.setText(currentMovie.getTitle());
                }

                // Set release date
                if (movieViewHolder.movie_category != null) {
                    movieViewHolder.movie_category.setText("" + currentMovie.getRelease_date());
                }

                // Set duration
                if (movieViewHolder.duration != null) {
                    movieViewHolder.duration.setText(" " + currentMovie.getMovie_id());
                }

                // Set rating
                if (movieViewHolder.ratingBar != null) {
                    movieViewHolder.ratingBar.setRating((currentMovie.getVote_average()) / 2);
                }
            }
            // Load image using Glide
            if (movieViewHolder.imageView != null) {
                Glide.with(movieViewHolder.itemView.getContext())
                        .load("https://image.tmdb.org/t/p/w500/" + currentMovie.getPoster_path())
                        .into(movieViewHolder.imageView);
            }
        }

    }

    @Override
    public int getItemCount() {

        if (mMovies != null){
            return mMovies.size();
        }
        return 0;
    }

    public List<MovieModel> getmMovies() {
        return mMovies;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}
