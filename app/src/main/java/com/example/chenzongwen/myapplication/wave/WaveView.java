package com.example.chenzongwen.myapplication.wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Owen Chan
 * DATE: 2016-10-11.
 */
public class WaveView extends View {

    private Paint mWavePaint;

    private Paint mCirclePaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;
    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);


    public WaveView(Context context) {
        super(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mWavePaint = new Paint();
        mWavePaint.setColor(Color.parseColor("#33b5e5"));
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.parseColor("#99cc00"));
        mBitmap = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888); //生成一个bitmap
        mCanvas = new Canvas(mBitmap);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        mCanvas.drawCircle(100,100,100,mCirclePaint);
        mWavePaint.setXfermode(mMode);
        mCanvas.drawRect(100,100,300,300,mWavePaint);
        canvas.drawBitmap(mBitmap,200,200,null);
        super.onDraw(canvas);
    }
}
