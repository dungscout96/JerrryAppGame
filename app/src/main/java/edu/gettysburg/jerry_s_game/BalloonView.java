package edu.gettysburg.jerry_s_game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by parker on 11/28/17.
 */

public class BalloonView extends View {
    private int nBalloons = 40;
    private int speed = 7;
    private int width;
    private int height;
    Canvas canvas1;
    private HashMap<Rect, Integer> balloons = new HashMap<Rect, Integer>();
    private ArrayList<ImageView> balloonSelector = new ArrayList<ImageView>();

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    boolean isGameOver = false;

    static final Paint whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    static final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public final Drawable[] balloonsDrawable = new Drawable[9];
    public final Bitmap[] bitmaps = new Bitmap[9];
    Rect[] destRects = new Rect[nBalloons];
    Integer[] destBalloons = new Integer[nBalloons];

    int balloonHeight;
    int balloonWidth;

    Rect srcRect;
    Rect destRect;

    //black, purple, blue, green, yellow, orange, red
    public static final Integer[] imageResIds = new Integer[]{0, R.drawable.black,
            R.drawable.purple,R.drawable.blue,R.drawable.green, R.drawable.yellow,
            R.drawable.orange,R.drawable.red, R.drawable.death};

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

        // TODO get actual width and height
        width = 1080;
        height = 1584;
        // we need to somehow be able to fix this: init() gets called before onDraw.
        // we can't get the width or height of the screen unless we are on that method
        // thus, we can't randomize where our balloons start before drawing them (absurd?)
        // unless we assume some width and height.
        // Maybe to fix it, we could not initialize the balloons and create an if statement in the
        // onDraw method such that when we update the width and the height (at the beginning 0)
        // we create new balloons.
        // So far it generates the balloons at the given width and height


        for (int i = 1; i < imageResIds.length; i++){
            balloonsDrawable[i] = res.getDrawable(imageResIds[i]);
            bitmaps[i] = BitmapFactory.decodeResource(res, imageResIds[i]);
        }

        balloonHeight = (bitmaps[1].getHeight() - 1) / 3;
        balloonWidth = (bitmaps[1].getWidth() - 1) / 3;

        //make n random balloons with random rectangles
        Random rand = new Random();

        for (int i = 0; i < nBalloons; i++) {
            int a = rand.nextInt(8) + 1;
            int left = rand.nextInt(width - balloonWidth);
            int top = rand.nextInt(2 * height);

            Rect dRect = new Rect(left, top, left + balloonWidth, top + balloonHeight);
            destRects[i] = dRect;
            destBalloons[i] = a; //so we can pair the color with the balloon

        }
        srcRect = new Rect(0, 0, bitmaps[1].getWidth() - 1, bitmaps[1].getHeight() - 1);
        destRect = new Rect(0, 0, balloonWidth, balloonHeight);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas1 = canvas;

//        if(getWidth() != width || getHeight() != height){
//            width = getWidth();
//            height = getHeight();
//          Maybe we don't need it
//          Maybe fix here the problem with the width and the height / rotation of the screen?
//        }
        width = getWidth();
        height = getHeight();

        canvas.drawRect(0,0,width,height,whitePaint);

        for (int i = 0; i < nBalloons; i++) {
            canvas1.drawBitmap(bitmaps[destBalloons[i]], srcRect, destRects[i], paint);
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                        invalidate();
                    }
                });
            }
        }, 20, Integer.MAX_VALUE);

        // makeBalloonsClickable();
    }

    public void changePos(){
        for (int i = 0; i < nBalloons; i++){
            Rect rect = destRects[i];

            if (rect.bottom < 0){
                destRects[i] = new Rect(rect.left, rect.top + 2*height, rect.right, rect.bottom + 2*height);
                // if they are on the top, we move them to the bottom. Below where the screen is

                // TODO update visibility here to make them visible again
            }
            else {
                destRects[i] = new Rect(rect.left, rect.top - speed, rect.right, rect.bottom - speed);
                // move them to the top
            }
        }


    }

    // TODO make balloons clickable
    /* make them invisible if clickable and make them visible again in changePos()
        also, set a counter for the score

     */

    /*
     // TODO set a timer
    We have to be able to lose somehow, right? And if we lose, save the High Score
     */

    /*public void makeBalloonsClickable() {

        for (ImageView balloon : balloons) {

            balloon.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Log.i("touched", "balloon touched");
                    //     balloon.setVisibility(View.INVISIBLE);
                    balloonTouched(view);
                    return false;
                }
            });
        }
    }

    public void balloonTouched(View view) {
        view.setVisibility(View.INVISIBLE);
    }*/
}