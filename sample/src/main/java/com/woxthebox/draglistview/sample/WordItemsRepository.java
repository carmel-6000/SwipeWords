package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.woxthebox.draglistview.swipe.WordItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garfield on 10/22/17.
 */

public class WordItemsRepository {


    private List<WordItem> wordItems;
    private int screenWidth;
    private ArrayList<Integer> indexes;

    public WordItemsRepository(Context context, List<WordItem> wordItems){
        this.screenWidth=getScreenWidth(context);
        this.screenWidth -= dpToPx((Activity)context,20*2);
        this.wordItems=wordItems;
        calcColSpans();
    }
    public static int dpToPx(Activity activity, int dp){
        return  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics());
    }

    private void calcColSpans(){

        //ArrayList<Integer> indexes=new ArrayList<>();


        int rowWidth=0,i;
        indexes=new ArrayList<Integer>();
        //screenWidth-=600;
        for (i=0;i<wordItems.size();i++){

            if (indexes==null){
                indexes=new ArrayList<Integer>();

            }
            indexes.add(Integer.valueOf(i));

            if (rowWidth+wordItems.get(i).getPxWidth()>screenWidth){
                Log.d("WordItemsRepository","Width Length of words exceeds screen width");

                setColspans(indexes);
                indexes=null;
                rowWidth=0;

            }else{

                Log.d("WordItemsRepository","Width Length of words DOESN'T exceed screen width, adding one more");
                rowWidth+=wordItems.get(i).getPxWidth();

            }
        }

        if (indexes!=null){
            setColspans(indexes);
            indexes=null;
            rowWidth=0;

        }

    }

    private void setColspans(List<Integer> indexes){
        for (int ii=0;ii<indexes.size();ii++){

            int columns=ListFragment.NUM_OF_COLS;
            float p=(float)wordItems.get( indexes.get(ii)).getPxWidth()/screenWidth;
            Log.d("WordItemRepository","GET PX WIDTH?"+wordItems.get( indexes.get(ii)).getPxWidth());
            int colSpan=columns;

            for (int x=0;x<=columns;x++){

                Log.d("WordItemRepository","P IS:"+p);
                Log.d("WordItemsRepository","x:"+x);
                Log.d("WordItemsRepository","x/columns:"+x/columns);
                Log.d("WordItemsRepository","(x+1)/columns:"+(x+1)/columns);
                if (p > (float)x/columns  &&  p <= ((float)(x+1))/columns ){ colSpan=x+1;}
            }

            wordItems.get( indexes.get(ii) ).setColSpan( colSpan );
            //wordItems.get( indexes.get(ii) ).setColSpan(2);
            Log.d("WordItemsRepository","Setting span ("+colSpan+") for word index ("
                    +indexes.get(ii)+") with string ("+wordItems.get( indexes.get(ii) ).getWord()
                    +")"+" and width:("+(int)p+")" );
        }
    }

    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }
}
