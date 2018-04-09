package com.example.adi.popularmovies.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.adi.popularmovies.PopularMovie;
import com.example.adi.popularmovies.PopularMoviesAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by adi on 26.02.2018.
 */


public class AdapterUpdater extends AsyncTask<PopularMoviesAdapter, Void, JSONObject> {

    PopularMoviesAdapter mAdapter;
    public boolean mclear;
    public String murl;

    public AdapterUpdater(PopularMoviesAdapter adapter, Context context, boolean clear) {
        mAdapter = adapter;
        mclear = clear;
        if (mAdapter.popular)
            murl = MovieDB.getMoviesListURL(context, true, this.mAdapter.current_page);
        else
            murl = MovieDB.getMoviesListURL(context, false, this.mAdapter.current_page);
    }

    @Override
    protected void onPreExecute() {
        if (mclear){
            mAdapter.notifyDataSetChanged();
            mAdapter.current_page = 1;
        }
    }

    @Override
    protected JSONObject doInBackground(PopularMoviesAdapter... popularMoviesAdapters) {

        JSONObject response = null;

        if (Network.hasInternet()){
            try {
                response = Network.getAsJson(this.murl);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(JSONObject jsonMovies) {

        PopularMovie[] moviesList = Converter.jsonToMovieList(jsonMovies);

        mAdapter.updateItems(moviesList, mclear);
    }
}
