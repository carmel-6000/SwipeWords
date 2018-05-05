package com.woxthebox.draglistview;

import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class LessonsLayoutManager extends StaggeredGridLayoutManager {

    private Point mMeasuredDimension = new Point();

    public LessonsLayoutManager() {
        super(2, HORIZONTAL);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {

        final int widthSize = View.MeasureSpec.getSize(widthSpec) - (getPaddingRight() + getPaddingLeft());

        int width = 0;
        int height = 0;
        int row = 0;
        Log.d("LessonsLayoutManager"," getItemCount():"+getItemCount());
/*
        for (int i = 0; i < getItemCount(); i++) {

            int x=View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED);
            Log.d("LessonsLayoutManager","Measure x:"+x);


            if (!measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension)) {
                Log.d("LessonsLayoutManager","No Measures for item:"+i);
                continue;

            }

            Log.d("LessonsLayoutManager","mMeasuredDimension.x:"+mMeasuredDimension.x);

            if (width + mMeasuredDimension.x > widthSize || mMeasuredDimension.x > widthSize) {
                row++;
                width = mMeasuredDimension.x;
            } else {
                width += mMeasuredDimension.x;
            }

            height += mMeasuredDimension.y;
        }
        */

        Log.d("LessonsLayoutManager","HEIGHT:"+height);
        Log.d("LessonsLayoutManager","WIDTH:"+width);
        setSpanCount(row);
        setSpanCount(2);

        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    private boolean measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, Point measuredDimension) {

        View view = null;
        try {
            view = recycler.getViewForPosition(position);
        } catch (Exception ex) {
            // try - catch is needed since support library version 24
        }
        Log.d("LessonsLayoutManager","View is null!!");

        if (view != null) {

            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();

            int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                    getPaddingLeft() + getPaddingRight(), p.width);
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                    getPaddingTop() + getPaddingBottom(), p.height);

            view.measure(childWidthSpec, childHeightSpec);

            measuredDimension.set(
                    view.getMeasuredWidth() + p.leftMargin + p.rightMargin,
                    view.getMeasuredHeight() + p.bottomMargin + p.topMargin
            );

            recycler.recycleView(view);

            return true;
        }

        return false;
    }

}