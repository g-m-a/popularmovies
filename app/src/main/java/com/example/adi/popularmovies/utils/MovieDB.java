package com.example.adi.popularmovies.utils;

import android.content.Context;

import com.example.adi.popularmovies.R;

public class MovieDB {
    public static final String API_KEY = "<REPLACE HERE>";

    public static String getMoviesListURL(Context context, boolean popular, int page){
        String urlFormat = context.getResources().getString(R.string.movie_list_url);
        String baseUrl = context.getResources().getString(R.string.base_url);

        String list_type;
        if (popular){
            list_type = context.getResources().getString(R.string.list_type_popular);
        }
        else{
            list_type = context.getResources().getString(R.string.list_type_top);
        }

        String list_page = String.format(context.getResources().getString(R.string.page), page);
        String api_key = String.format(context.getResources().getString(R.string.key), MovieDB.API_KEY);

        return String.format(urlFormat, baseUrl, list_type, list_page, api_key);
    }


    public static String getMovieImageURL(Context context, String poster_url, boolean thumb){
        String urlFormat = context.getResources().getString(R.string.movie_image_url);
        String baseUrl = context.getResources().getString(R.string.image_url_base);

        String img_type;
        if (thumb){
            img_type = context.getResources().getString(R.string.thumb);
        }
        else{
            img_type = context.getResources().getString(R.string.poster);
        }

        return String.format(urlFormat, baseUrl, img_type, poster_url);
    }

    public static String getMovieVideoURL(Context context, String movie_id){
        String urlFormat = context.getResources().getString(R.string.movie_videos_url);
        String baseUrl = context.getResources().getString(R.string.base_url);

        String videos_part = context.getResources().getString(R.string.trailers);
        String api_key = String.format(context.getResources().getString(R.string.key), MovieDB.API_KEY);


        return String.format(urlFormat, baseUrl, movie_id, videos_part, api_key);
    }




}
