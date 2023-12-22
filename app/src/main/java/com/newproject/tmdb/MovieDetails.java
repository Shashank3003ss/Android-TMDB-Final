package com.newproject.tmdb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newproject.tmdb.models.MovieModel;

public class MovieDetails extends AppCompatActivity {

    private ImageView imageViewDetails;
    private TextView titleDetails, overviewDetails, vote, OD, Pop;
    private RatingBar ratingBarDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageViewDetails = findViewById(R.id.imageViewdetails);
        titleDetails = findViewById(R.id.textView_title_details);
        overviewDetails = findViewById(R.id.textView_desc_details);
        ratingBarDetails = findViewById(R.id.ratingBar_details);
        float rating = ratingBarDetails.getRating();
        vote = findViewById(R.id.textViewVote);
        OD = findViewById(R.id.textViewOD);

        GetDataFromIntent();
    }

    private void GetDataFromIntent(){

        if (getIntent().hasExtra("movie")){
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            if (movieModel != null){
                titleDetails.setText(movieModel.getTitle());

                overviewDetails.setText("Runtime : " +movieModel.getRuntime()+"mins");
                vote.setText("Vote count : "+ movieModel.getVote_count());

                OD.setText(" " + movieModel.getMovie_overview());

//            Log.v("Tag", "X" + movieModel.getMovie_overview());

                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/original/"
                                + movieModel.getPoster_path())
                        .into(imageViewDetails);
            }



        }

    }

}