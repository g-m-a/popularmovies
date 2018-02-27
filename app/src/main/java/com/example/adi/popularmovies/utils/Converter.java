package com.example.adi.popularmovies.utils;

import com.example.adi.popularmovies.PopularMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by adi on 26.02.2018.
 */

public class Converter {
    public static ArrayList<PopularMovie> jsonToMovieList(JSONObject jsonMovies){
        ArrayList<PopularMovie> movies = new ArrayList<>();

        try {
            JSONArray array = jsonMovies.getJSONArray("results");
            if (array.length() > 0){
                for (int i=0; i<array.length(); i++){
                    JSONObject movie = array.getJSONObject(i);

                    movies.add(new PopularMovie(movie.getString("title"),
                            movie.getString("poster_path"),
                            movie.getString("overview"),
                            new Double(movie.getDouble("vote_average")).floatValue(),
                            movie.getString("release_date")));
                }
            }
        } catch (JSONException e) {
            return movies;
        } catch (NullPointerException e){
            return movies;
        }

        return movies;
    }
}
