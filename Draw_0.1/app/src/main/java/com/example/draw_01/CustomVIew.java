package com.example.draw_01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class CustomVIew extends View {
    List<Path>listStrokes = new ArrayList<Path>();
    Path pathStroke;
    Bitmap memBMP;
    Paint memPaint;
    Canvas memCanvas;
    boolean mBooleanOnTouch = false;

    float oldx;
    float oldy;

    public CustomVIew(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                pathStroke = new Path();
                pathStroke.moveTo(x,y);
                oldx = x;
                oldy = y;
                mBooleanOnTouch = true;
                listStrokes.add(pathStroke);
                break;
            /*case MotionEvent.ACTION_MOVE:
                if(mBooleanOnTouch){
                    pathStroke.quadTo(oldx,oldy,x,y);
                    oldx = x;
                    oldy = y;
                    drawStrokes();
                }
                break;*/
            case MotionEvent.ACTION_UP:
                if(mBooleanOnTouch){
                    pathStroke.quadTo(oldx,oldy,x,y);
                    drawStrokes();
                    mBooleanOnTouch = false;
                }
                break;
        }
        return true;
    }

    private void drawStrokes() {
        if (memCanvas == null) {    //缓冲位
            memBMP = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            memCanvas = new Canvas(); //缓冲画布
            memCanvas.setBitmap(memBMP); //为画布设置位图，图形实际保存在位图中
            memPaint = new Paint(); //画笔
            memPaint.setAntiAlias(true); //抗锯齿
            memPaint.setColor(Color.RED); //画笔颜色
            memPaint.setStyle(Paint.Style.STROKE); //设置填充类型
            memPaint.setStrokeWidth(5); //设置画笔宽度
        }
        for (Path path : listStrokes) {
            memCanvas.drawPath(path, memPaint);
        }
        invalidate(); //刷新屏幕
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        if(memBMP != null)
            canvas.drawBitmap(memBMP,0,0,paint);
    }
}
