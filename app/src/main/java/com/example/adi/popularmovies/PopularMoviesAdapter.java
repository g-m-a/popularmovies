package com.example.adi.popularmovies;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.adi.popularmovies.utils.MovieDB;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Created by adi on 26.02.2018.
 */

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {
    public int current_page;
//    public boolean popular;
    public int list_type;
    public PopularMovie[] mData;
    private LayoutInflater mInflater;

    PopularMoviesAdapter(Context context, PopularMovie[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.current_page = 1;
        this.list_type = 1;
    }

    @Override
    public PopularMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_item, parent, false);

        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        if (view.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            lp.height = (int) parent.getMeasuredHeight()/3;
        } else {
            lp.height = (int) parent.getMeasuredHeight()/4;
        }
        view.setLayoutParams(lp);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularMoviesAdapter.ViewHolder holder, int position) {
        String thumb_path = MovieDB.getMovieImageURL(mInflater.getContext(),  mData[position].poster_url.replace("\\/", ""), true);
        Picasso.with(mInflater.getContext()).load(thumb_path).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClick(v, getAdapterPosition());
        }
    }

    public PopularMovie getItem(int i){
        return mData[i];
    }

    public void onItemClick(View v, int position){
        Intent intent = new Intent(v.getContext(), DetailsActivity.class);
        PopularMovie movie = mData[position];
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(PopularMovie.INTENT_EXTRA_LABEL, movie);
        intent.putExtras(mBundle);
        mInflater.getContext().startActivity(intent);

    }


    public void updateItems(PopularMovie[] movies, boolean clear){

        if (clear){
            this.mData = movies;
        }
        else{
            this.mData = (PopularMovie[]) ArrayUtils.addAll(this.mData, movies);
        }

        this.notifyDataSetChanged();

        View root = ((Activity)mInflater.getContext()).getWindow().getDecorView().findViewById(R.id.main_view);
        if (root != null)
        {
            ImageView loading = (ImageView) root.findViewById(R.id.loading);
            if (loading != null)
                loading.setVisibility(View.INVISIBLE);
        }

    }
}
