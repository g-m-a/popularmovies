package com.example.adi.popularmovies.utils;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemSpacing extends RecyclerView.ItemDecoration {
    private int space;

    public ItemSpacing(int space) {
        super();
        this.space = space;
    }

    @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        float spanSize = gridLayoutManager.getSpanSizeLookup().getSpanSize(position);
        float totalSpanSize = gridLayoutManager.getSpanCount();

        float n = totalSpanSize / spanSize; // num columns
        float c = layoutParams.getSpanIndex() / spanSize; // column index

        float leftPadding = space * ((n - c) / n);
        float rightPadding = space * ((c + 1) / n);

        outRect.left = (int) leftPadding;
        outRect.right = (int) rightPadding;
        outRect.bottom = space;

        if (parent.getChildLayoutPosition(view) < ((GridLayoutManager) parent.getLayoutManager()).getSpanCount()) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }

    }
}