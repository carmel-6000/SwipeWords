package com.woxthebox.draglistview.swipe;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by garfield on 10/22/17.
 */

public class WordItem {

    private String word;
    private int colSpan=1;
    private Context context;
    private int pxWidth;
    final private static int TEXT_SIZE=18;
    final private static int TOP_PADDING=12;
    final private static int BOTTOM_PADDING=12;
    final private static int LEFT_PADDING=7;
    final private static int RIGHT_PADDING=7;
    public int index;


    public WordItem(Context context){

        this.context=context;
        this.word=getSaltString();
        int screenWidth=getScreenWidth(context);
        Typeface typeface= Typeface.DEFAULT;
        int textWidth=getTextViewWidth(context,this.word,TEXT_SIZE+4,screenWidth,typeface);
        this.pxWidth=textWidth;

    }

    protected String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        int max=10;
        int min=3;
        //int len = rnd.nextInt(max - min + 1) + min;
        int len = 5;//rnd.nextInt(max - min + 1) + min;
        while (salt.length() < len) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();

        return saltStr;

    }

    public String getWord(){
        return word;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;

    }

    public int getPxWidth() {
        return pxWidth;
    }

    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }


    public static int getTextViewWidth(Context context, CharSequence text, int textSize, int deviceWidth, Typeface typeface) {
        TextView textView = new TextView(context);
        textView.setPadding(LEFT_PADDING,TOP_PADDING,RIGHT_PADDING,BOTTOM_PADDING);

        textView.setTypeface(typeface);
        textView.setText(text, TextView.BufferType.SPANNABLE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredWidth();
    }


}

