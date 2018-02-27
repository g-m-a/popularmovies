package com.example.adi.popularmovies;

import android.app.Activity;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.adi.popularmovies.utils.Config;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi on 26.02.2018.
 */

public class PopularMoviesAdapter extends ArrayAdapter<PopularMovie> {
    public boolean popular;
    public int current_page;
    public boolean clear;

    public PopularMoviesAdapter(@NonNull Context context, List<PopularMovie> popularMovieList) {
        super(context, 0, popularMovieList);
        this.current_page = 1;
        this.popular = true;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.poster);
        String thumb_path = Config.URL_THUMB + Config.THUMB_SIZE + getItem(position).poster_url.replace("\\/", "");

        Picasso.with(getContext()).load(thumb_path).into(imageView);

        return convertView;
    }

    public void updateItems(ArrayList<PopularMovie> movies){

        this.addAll(movies);
        this.notifyDataSetChanged();

        View root = ((Activity)getContext()).getWindow().getDecorView().findViewById(R.id.main_view);
        if (root != null)
        {
            ImageView loading = (ImageView) root.findViewById(R.id.loading);
            if (loading != null)
                loading.setVisibility(View.INVISIBLE);
        }

    }
}
