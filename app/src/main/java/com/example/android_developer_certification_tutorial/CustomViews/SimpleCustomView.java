package com.example.android_developer_certification_tutorial.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.android_developer_certification_tutorial.R;


public class SimpleCustomView extends View{
    Paint mPaint;
    Rect mRect;
    int mSquareColor;
    static int mPadding = 0;

    public SimpleCustomView(Context context) {
        super(context);
        init(null);
    }

    public SimpleCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SimpleCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    private void init(@Nullable AttributeSet set){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();

        if(set == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.SimpleCustomView);
        mSquareColor = ta.getColor(R.styleable.SimpleCustomView_square_color, Color.GREEN);
        mPaint.setColor(mSquareColor);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.left = 0;
        mRect.right = getWidth();
        mRect.top = 0;
        mRect.bottom = getHeight();

        canvas.drawRect(mRect, mPaint);
    }

    public void swapColor(){
        mPaint.setColor(mPaint.getColor()==mSquareColor?Color.RED:mSquareColor);
        postInvalidate();
    }

    public void customPaddingUp(int padding){
        mPadding = mPadding + padding;
        postInvalidate();
    }

    public void customPaddingDown(int padding){
        mPadding = mPadding -  padding;
        postInvalidate();
    }
}