package com.hcmes.viewdemo.l12;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.hcmes.viewdemo.R;

//    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
public class WindowDialog {
    Context context;
    WindowManager.LayoutParams mLayoutParams;
    public WindowDialog(Context context) {
        this.context = context;

    }
    float moveX,moveY,rowX,rowY;

    /**
     * 获取状态栏高度
     *
     * @param context 目标Context
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public void show(){
        WindowManager mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams =new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager . LayoutParams.
                WRAP_CONTENT , 2099 ,
                WindowManager .LayoutParams . FLAG_NOT_TOUCH_MODAL|
                        WindowManager . LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager .LayoutParams.FLAG_SHOW_WHEN_LOCKED,
                PixelFormat.TRANSPARENT);
        mLayoutParams.type = WindowManager .LayoutParams.TYPE_SYSTEM_ERROR;
        mLayoutParams.gravity = Gravity.TOP|Gravity . LEFT;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0 ;
        ImageView view= new ImageView(context);
        view.setImageResource(R.drawable.pic_1);
        GestureDetector gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                mWindowManager.removeViewImmediate(view);
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveX = event.getX();
                        moveY = event.getY();
                        rowX = event.getRawX();
                        rowY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mLayoutParams.x = (int) (event.getRawX()-moveX);
                        mLayoutParams.y= (int) (event.getRawY()-moveY-getStatusBarHeight(context));
                        mWindowManager.updateViewLayout(view, mLayoutParams);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return   gestureDetector.onTouchEvent(event);
            }
        });
        mWindowManager.addView(view, mLayoutParams) ;
    }
}
