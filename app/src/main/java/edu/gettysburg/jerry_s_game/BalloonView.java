package edu.gettysburg.jerry_s_game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by parker on 11/28/17.
 */

public class BalloonView extends View {

    //fields

    boolean isGameOver = false;
    
    static final Paint whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public final Drawable[] balloons = new Drawable[8];
    public final Bitmap[] bitmaps = new Bitmap[8];

    //order of difficulty:
    //black, purple, blue, green, yellow, orange, red
    public static final Integer[] imageResIds = new Integer[]{0, R.drawable.black,
            R.drawable.purple,R.drawable.blue,R.drawable.green, R.drawable.yellow,
            R.drawable.orange,R.drawable.red,R.drawable.death,};

    Resources res = null;


    static{
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);
    }


    public BalloonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        init();
    }


    private void init(){
        isGameOver = false;

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();



        canvas.drawRect(0,0,width,height, whitePaint);


    }



}
