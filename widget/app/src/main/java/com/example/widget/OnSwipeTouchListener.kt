package com.example.widget

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

open class OnSwipeTouchListener(private val context: Context?) : OnTouchListener {
    private val gestureDetector = GestureDetector(context, GestureListener())
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(motionEvent)
    }

    protected open fun onSwipeLeft(): Boolean {
        return false
    }

    protected open fun onSwipeRight(): Boolean {
        return false
    }

    internal inner class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
            return super.onDoubleTapEvent(e)
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            try {
                Log.i("test", "swipfling")
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SwipeThreshold && Math.abs(velocityX) > SwipeVelocityThreshold) {
                        if (diffX > 0) onSwipeRight() else onSwipeLeft()
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return false
        }
    }

    companion object {
        private const val SwipeThreshold = 100
        private const val SwipeVelocityThreshold = 100
    }
}