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

    private ImageView imageViewDetails, poster;
    private TextView titleDetails, overviewDetails, vote, OD, Pop, OriginalLanguage, ov, popularity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        imageViewDetails = findViewById(R.id.imageViewdetails);
        titleDetails = findViewById(R.id.textView_title_details);
        overviewDetails = findViewById(R.id.textView_desc_details);
        vote = findViewById(R.id.textViewVote);
        OD = findViewById(R.id.textViewOD);
        Pop = findViewById(R.id.textViewPop);
        OriginalLanguage = findViewById(R.id.textViewOL);
        poster = findViewById(R.id.imageView);
        ov = findViewById(R.id.ov);
        popularity = findViewById(R.id.textViewPopularity);

        GetDataFromIntent();
    }

    private void GetDataFromIntent(){

        if (getIntent().hasExtra("movie")){
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            if (movieModel != null){
                titleDetails.setText(movieModel.getTitle());

                overviewDetails.setText("Runtime : " +movieModel.getRuntime()+" mins");
                vote.setText("Vote count : "+ movieModel.getVote_count());
                OD.setText("Release Date : " + movieModel.getRelease_date());
                Pop.setText("Movie ID : " + movieModel.getMovie_id());
                OriginalLanguage.setText("Original Language : " + movieModel.getOriginal_language());
                popularity.setText("Popularity : " + movieModel.getPopularity());
//            Log.v("Tag", "X" + movieModel.getMovie_overview());

                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/original/" + movieModel.getBackdrop_path())
                        .error(R.drawable.default_image)
                        .into(imageViewDetails);
                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/original/" + movieModel.getPoster_path())
                        .error(R.drawable.default_image)
                        .into(poster);

                ov.setText("OverView : " + movieModel.getMovie_overview());
            }



        }

    }

}