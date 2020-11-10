package com.example.plot_draw01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
    String math_str;
    public DrawView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }
    public String getMath_str(){
        return math_str;
    }
    public void setMath_str(String math_str){
        this.math_str = math_str;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(math_str == null || math_str.length() == 0)
            return;
        Rect rect=new Rect();
        rect.left=0;
        rect.top=0;
        rect.right = getWidth();
        rect.bottom=getHeight();

        Axis axis=new Axis(rect);


        axis.draw(canvas);
        Plot plot=new Plot(axis,strFunction,"x");
        plot.draw(canvas);
    }
}
