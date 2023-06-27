package com.example.widget;


import static java.lang.Math.abs;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private static final int SwipeThreshold = 100;
    private static final int SwipeVelocityThreshold = 100;
    private Context context;
    public OnSwipeTouchListener(Context context)
    {
        this.context = context;
    }
    private GestureDetector gestureDetector = new GestureDetector(context, new GestureListener());

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);

    }
    protected boolean onSwipeLeft() {
        return false;
    }
    protected boolean onSwipeRight() {
        return false;
    }

    class GestureListener extends  GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                Log.i("test","swipfling");
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (abs(diffX) > abs(diffY)) {
                    if (abs(diffX) > SwipeThreshold && abs(velocityX) > SwipeVelocityThreshold) {
                        if(diffX > 0 )
                            onSwipeRight();
                        else
                            onSwipeLeft();
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }
}

