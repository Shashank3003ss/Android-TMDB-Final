package com.newproject.tmdb.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.newproject.tmdb.AppExecutors;
import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.response.MovieSearchResponse;
import com.newproject.tmdb.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    //making  Global runnable
    private RetriveMoviesRunnable retriveMoviesRunnable;

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }


    public void searchMoviesApi(String query, int pageNumber) {

        if (retriveMoviesRunnable!=null){
            retriveMoviesRunnable=null;
        }

        retriveMoviesRunnable = new RetriveMoviesRunnable(query, pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retriveMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    //Retrieving data from RestApi by runnable class
    private class RetriveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetriveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = cancelRequest;
        }

        @Override
        public void run() {
            try{
                Response response=getMovies(query,pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code()==200){
                    List<MovieModel>list=new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
//                    Sending data to live data
//                    Post value for background thread
//                    Set value not for background thread
                    if(pageNumber==1){
                        mMovies.postValue(list);
                    }
                    else{
                        List<MovieModel>currentMovies=mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }

                }
                else{
                    String  error=response.errorBody().string();
                    Log.v("Tag","Error"+error);
                    mMovies.postValue(null);
                }

            }
            catch (IOException e){
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<MovieSearchResponse> getMovies(String query,int pageNumber){
            Service service=new Service();
            return service.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );

        }
        private void CancelRequest(){
            Log.v("TAg","Cancelling Search Request");
            cancelRequest=true;
        }
    }
}
