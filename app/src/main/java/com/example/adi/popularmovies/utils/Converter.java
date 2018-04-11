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
    public static PopularMovie[] jsonToMovieList(JSONObject jsonMovies){
        ArrayList<PopularMovie> movies = new ArrayList<>();

        try {
            JSONArray array = jsonMovies.getJSONArray("results");
            if (array.length() > 0){
                for (int i=0; i<array.length(); i++){
                    JSONObject movie = array.getJSONObject(i);

                    movies.add(new PopularMovie(
                            movie.getString(MovieDB.TITLE_JSON_KEY),
                            movie.getString(MovieDB.POSTER_JSON_KEY),
                            movie.getString(MovieDB.OVERVIEW_JSON_KEY),
                            new Double(movie.getDouble(MovieDB.RATING_JSON_KEY)).floatValue(),
                            movie.getString(MovieDB.RELEASE_JSON_KEY),
                            movie.getString(MovieDB.ID_JSON_KEY)));
                }
            }
        } catch (JSONException e) {

            return movies.toArray(new PopularMovie[movies.size()]);
        } catch (NullPointerException e){
            return movies.toArray(new PopularMovie[movies.size()]);
        }

        return movies.toArray(new PopularMovie[movies.size()]);
    }
}
