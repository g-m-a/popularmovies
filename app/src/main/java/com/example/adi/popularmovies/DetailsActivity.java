package com.example.adi.popularmovies;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.adi.popularmovies.utils.Config;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(getString(R.string.details_title));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        PopularMovie movie = (PopularMovie) intent.getSerializableExtra(PopularMovie.INTENT_EXTRA_LABEL);

        TextView title = (TextView) findViewById(R.id.title);
        TextView rating = (TextView) findViewById(R.id.rating);
        ImageView poster = (ImageView) findViewById(R.id.poster);
        TextView release_date = (TextView) findViewById(R.id.release_date);
        TextView overview = (TextView) findViewById(R.id.overview);


        String thumb_path = Config.URL_THUMB + Config.POSTER_SIZE + movie.poster_url;
        Picasso.with(this).load(thumb_path).into(poster);

        title.setText(movie.title);
        rating.setText(Float.toString(movie.rating));
        release_date.setText(movie.release_date);
        overview.setText(movie.overview);

    }
}
