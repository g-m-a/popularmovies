package com.example.adi.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavContract {
    public static final String AUTHORITY = "com.example.adi.popularmovies";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_FAVORITES = "favorites";

    public static final class FavEntry implements BaseColumns {
        public static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();

        public static String TABLE_NAME = "favorites";
        public static final String COLUMN_MOVIE_REF = "movie_ref";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";
    }
}
