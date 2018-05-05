package com.woxthebox.draglistview;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
//import com.woxthebox.draglistview.sample;
import com.woxthebox.draglistview.swipe.WordItem;

import java.util.List;

/**
 * Created by garfield on 10/21/17.
 */

public class MyGridLayoutManager extends GridLayoutManager {

    private Point mMeasuredDimension = new Point();
    private List<WordItem> wordItems;

    public MyGridLayoutManager(Context context, int spanCount,List<WordItem> wordItems) {
        //super(2, HORIZONTAL);

        super(context, spanCount);
        this.wordItems=wordItems;
        setSpan();
    }

    private void setSpan(){

        this.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                return wordItems.get(position).getColSpan();

            }
        });
    }
    /*
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {

    }
    */
    /*
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {

        Log.d("MyGridLayoutManager","widthSpec:"+View.MeasureSpec.getSize(widthSpec));
        Log.d("MyGridLayoutManager","heightSpec:"+heightSpec);
        int wSpec=View.MeasureSpec.getSize(widthSpec);
        //wSpec-=300;
        setMeasuredDimension(wSpec, View.MeasureSpec.getSize(heightSpec));

        for (int i = 0; i < getItemCount(); i++) {

            View view = null;
            try {
                view = recycler.getViewForPosition(i);
            } catch (Exception ex) {
                // try - catch is needed since support library version 24
            }
            Log.d("MyGridLayoutManager","view for pos ("+i+") is null?"+(view==null));
            if (!measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension)) {

                Log.d("GridLayoutManager","No Measures for item:"+i);
                continue;

            }

        }

        //setSpanCount(4);//how many items in a row
    }
    */

    /*
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {


        final int widthSize = View.MeasureSpec.getSize(widthSpec) - (getPaddingRight() + getPaddingLeft());

        int width = 0;
        int height = 0;
        int row = 0;
        Log.d("GridLayoutManager"," getItemCount():"+getItemCount());

        for (int i = 0; i < getItemCount(); i++) {

            int x=View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED);
            Log.d("GridLayoutManager","Measure x:"+x);


            if (!measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension)) {
                Log.d("GridLayoutManager","No Measures for item:"+i);
                continue;

            }

            Log.d("GridLayoutManager","mMeasuredDimension.x:"+mMeasuredDimension.x);

            if (width + mMeasuredDimension.x > widthSize || mMeasuredDimension.x > widthSize) {
                row++;
                width = mMeasuredDimension.x;
            } else {
                width += mMeasuredDimension.x;
            }

            height += mMeasuredDimension.y;
        }

        Log.d("GridLayoutManager","HEIGHT:"+height);
        Log.d("GridLayoutManager","WIDTH:"+width);
        //setSpanCount(row);
        setSpanCount(2);

        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);

    }
    */

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

}
