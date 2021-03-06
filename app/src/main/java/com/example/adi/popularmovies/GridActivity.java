package com.example.adi.popularmovies;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adi.popularmovies.utils.AdapterUpdater;
import com.example.adi.popularmovies.utils.Config;
import com.example.adi.popularmovies.utils.ItemSpacing;
import com.example.adi.popularmovies.utils.MovieDB;
import com.example.adi.popularmovies.utils.Network;

import java.io.Serializable;


public class GridActivity extends AppCompatActivity {
    PopularMoviesAdapter movies_adapter = null;
    Menu menu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        PopularMovie[] popularMovieList = new PopularMovie[0];


        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, Config.LIST_COL_NUMBER, RecyclerView.VERTICAL, false);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
          layoutManager.setSpanCount(Config.LIST_COL_NUMBER + 1);
        }

        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new ItemSpacing(30));

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if ((pastVisibleItems + visibleItemCount >= totalItemCount - Config.LIST_COL_NUMBER) && !rv.canScrollVertically(1)) {
                    if (Network.hasNetwork(getApplicationContext())){
                        ((ImageView) findViewById(R.id.loading)).setVisibility(View.VISIBLE);
                        movies_adapter.current_page += 1;
                        new AdapterUpdater(movies_adapter, getApplicationContext(), false).execute();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        movies_adapter = new PopularMoviesAdapter(this, popularMovieList);

        rv.setAdapter(movies_adapter);

        ImageView loading = (ImageView) findViewById(R.id.loading);
        Glide.with(this)
                .asGif()
                .load(R.drawable.loading)
                .into(loading);


        if (movies_adapter.mData.length == 0)
            new AdapterUpdater(movies_adapter, getApplicationContext(),false).execute();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MovieDB.INSTANCE_MOVIES_KEY, movies_adapter.mData);
        outState.putInt(MovieDB.INSTANCE_LIST_KEY, movies_adapter.list_type);
        outState.putInt(MovieDB.INSTANCE_PAGE_KEY, movies_adapter.current_page);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        movies_adapter.mData = (PopularMovie[]) savedInstanceState.getSerializable("movies");
        movies_adapter.list_type = savedInstanceState.getInt(MovieDB.INSTANCE_LIST_KEY);
        movies_adapter.current_page = savedInstanceState.getInt(MovieDB.INSTANCE_PAGE_KEY);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Network.hasNetwork(getApplicationContext())) {
            switch (item.getItemId()) {
                case R.id.popular_item:
                    item.setEnabled(false);
                    this.menu.findItem(R.id.top_rated_item).setEnabled(true);
                    movies_adapter.list_type = 1;
                    new AdapterUpdater(movies_adapter, getApplicationContext(), true).execute();
                    getSupportActionBar().setTitle(R.string.popular_title);
                    return true;
                case R.id.top_rated_item:
                    item.setEnabled(false);
                    this.menu.findItem(R.id.popular_item).setEnabled(true);
                    movies_adapter.list_type = 2;
                    new AdapterUpdater(movies_adapter, getApplicationContext(), true).execute();
                    getSupportActionBar().setTitle(R.string.top_title);
                    return true;
                case R.id.favorites_item:
                    item.setEnabled(false);
                    this.menu.findItem(R.id.favorites_item).setEnabled(true);
                    movies_adapter.list_type = 3;
//                    new AdapterUpdater(movies_adapter, getApplicationContext(), true).execute();
                    getSupportActionBar().setTitle(R.string.top_title);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        }
    }
}
