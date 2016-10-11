package com.example.chenzongwen.myapplication.wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.chenzongwen.myapplication.R;

/**
 * Author: Owen Chan
 * DATE: 2016-10-11.
 */
public class WaveLoadingView extends View {
    private final Paint mSRCPaint;

    private Paint mPaint;
    private Paint mTextPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private int y;
    private int x;

    private Path mPath;
    private boolean isLeft;
    private int mWidth;
    private int mHeight;
    private int mPercent;
    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public WaveLoadingView(Context context) {
        this(context, null);
    }

    public WaveLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#8800ff66"));

        mPath = new Path();
        mSRCPaint = new Paint();
        mSRCPaint.setAntiAlias(true);
        mSRCPaint.setColor(Color.parseColor("#88dddddd"));
        mBitmap = Bitmap.createBitmap(getResources().getDimensionPixelSize(R.dimen.bg_size),
                getResources().getDimensionPixelSize(R.dimen.bg_size), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
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

        y = mHeight;
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (x > 100) {
            isLeft = true;
        } else if (x < 0) {
            isLeft = false;
        }

        if (isLeft) {
            x = x - 2;
        } else {
            x = x + 2;
        }
        mPath.reset();
        if (mPercent != 0) {
            y = (int) ((1 - mPercent /100f) *mHeight);
            mPath.moveTo(0, y);
            mPath.cubicTo(100 + x * 2, 50 + y, 100 + x * 2, y - 50, mWidth, y);
            mPath.lineTo(mWidth, mHeight);
            mPath.lineTo(0, mHeight);
            mPath.close();
        }
        //清除掉图像 不然path会重叠
        mBitmap.eraseColor(Color.parseColor("#00000000"));
        mCanvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mSRCPaint);

        mPaint.setXfermode(mMode);
        mCanvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);

        canvas.drawBitmap(mBitmap, 0, 0, null);

        mTextPaint.setTextSize(80);
        float strLen = mTextPaint.measureText(mPercent + "");
        canvas.drawText(mPercent + "", mWidth / 2 - strLen / 2, mHeight / 2+15, mTextPaint);
        mTextPaint.setTextSize(40);
        canvas.drawText("%", mWidth / 2 + 50, mHeight / 2 - 20, mTextPaint);

        postInvalidateDelayed(10);
    }


    public void setPercent(int percent){
        mPercent = percent;
    }

}
