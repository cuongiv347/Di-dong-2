package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class TestCanvas extends View {
    Paint pacmanPaint;
    Paint eyeOfPacmanPaint;
    Paint pointEat;
    private RectF mArcBounds = new RectF();
    private Paint mCirclePaint;
    private Paint mEyeAndMouthPaint;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    Path path = new Path();
    Paint paint = new Paint();

    public TestCanvas(Context context) {
        super(context);
        init();
    }
    public TestCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TestCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pacmanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pacmanPaint.setColor(getResources().getColor(R.color.colorPrimary));
        eyeOfPacmanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eyeOfPacmanPaint.setColor(getResources().getColor(R.color.colorAccent));
        pointEat = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointEat.setColor(8 *getResources().getColor(R.color.colorPrimary));
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.YELLOW);
        mEyeAndMouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEyeAndMouthPaint.setStyle(Paint.Style.STROKE);
        mEyeAndMouthPaint.setStrokeWidth(8 * getResources().getDisplayMetrics().density);
        mEyeAndMouthPaint.setStrokeCap(Paint.Cap.ROUND);
        mEyeAndMouthPaint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16 * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int square = 300;
        float top = 100;
        float left = 100;
        float right = left + square;
        float bottom = top + square;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(left, top, right, bottom, 30, 300, true, pacmanPaint);
        }

        float cx = left + 180;
        float cy = top + 70;
        float radius = 25;
        float cpx = 200;
        float cpy =100;
        canvas.drawCircle(cx, cy, radius, eyeOfPacmanPaint);
        canvas.drawCircle(cx+cpx, cy+cpy, radius, eyeOfPacmanPaint);
        canvas.drawCircle(cx+cpx*2, cy+cpy, radius, eyeOfPacmanPaint);
        canvas.drawCircle(cx+cpx*3, cy+cpy, radius, eyeOfPacmanPaint);


//        canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);
        // draw eyes
        float eyeRadius = mRadius / 5f;
        float eyeOffsetX = mRadius / 3f;
        float eyeOffsetY = mRadius / 3f;
//        canvas.drawCircle(mCenterX - eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius, mEyeAndMouthPaint);
//        canvas.drawCircle(mCenterX + eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius, mEyeAndMouthPaint);
        canvas.drawPoint(mCenterX + eyeOffsetX, mCenterY - eyeOffsetY,pointEat);
        // draw mouth
        float mouthInset = mRadius / 3f;
//        mArcBounds.set(mouthInset, mouthInset, mRadius * 2 - mouthInset, mRadius * 2 - mouthInset);
        canvas.drawArc(mArcBounds, 45f, 90f, false, mEyeAndMouthPaint);
        canvas.drawPath(path,paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                path.addCircle(eventX, eventY, 10, Path.Direction.CW);
                break;
            case MotionEvent.ACTION_CANCEL: {
                path.addCircle(eventX, eventY, 10, Path.Direction.CW);
                break;
            }
            default:
                return false;
        }
        invalidate();
        return true;
    }
}
