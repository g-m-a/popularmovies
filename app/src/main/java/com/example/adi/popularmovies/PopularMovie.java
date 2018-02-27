package com.example.adi.popularmovies;

import java.net.URI;
import java.net.URL;

/**
 * Created by adi on 26.02.2018.
 */

public class PopularMovie {
    String title;
    String poster_url;
    String overview;
    Float rating;
    String release_date;

    public PopularMovie(String title, String poster_url, String overview, Float rating, String release_date){
        this.title = title;
        this.poster_url = poster_url;
        this.overview = overview;
        this.rating = rating;
        this.release_date = release_date;
    }


}
