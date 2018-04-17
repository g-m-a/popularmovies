package com.example.adi.popularmovies.utils;

import android.content.Context;

import com.example.adi.popularmovies.R;

public class MovieDB {
    public static final String API_KEY = "<YOUR API KEY HERE>";

    public static final String TITLE_JSON_KEY = "title";
    public static final String POSTER_JSON_KEY = "poster_path";
    public static final String OVERVIEW_JSON_KEY = "overview";
    public static final String RATING_JSON_KEY = "vote_average";
    public static final String RELEASE_JSON_KEY = "release_date";
    public static final String ID_JSON_KEY = "id";

    public static final String INSTANCE_MOVIES_KEY = "movies";
    public static final String INSTANCE_LIST_KEY = "list_type";
    public static final String INSTANCE_PAGE_KEY = "page";

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


    public static String getMovieReviews(Context context, String movie_id){
        String urlFormat = context.getResources().getString(R.string.movie_reviews_url);
        String baseUrl = context.getResources().getString(R.string.base_url);

        String reviews_part = context.getResources().getString(R.string.reviews);
        String api_key = String.format(context.getResources().getString(R.string.key), MovieDB.API_KEY);

        return String.format(urlFormat, baseUrl, movie_id, reviews_part, api_key);
    }




}
