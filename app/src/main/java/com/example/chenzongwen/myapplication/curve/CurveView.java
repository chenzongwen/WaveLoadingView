package com.example.chenzongwen.myapplication.curve;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author: Owen Chan
 * DATE: 2016-10-10.
 */

public class CurveView extends View{

    private float mSupX;
    private float mSupY;

    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private Path mPath;

    public CurveView(Context context) {
        super(context);
    }

    public CurveView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(mWidth / 5, mHeight / 2);  //设置起点
        mPath.quadTo(mSupX, mSupY, mWidth * 4 / 5, mHeight / 2);  //设置辅助点和终点
        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(mSupX, mSupY, mPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mSupX = event.getX();
                mSupY = event.getY();
                invalidate();
        }
        return true;
    }
}
