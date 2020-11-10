package com.example.note_sqllite;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * 处理图片的工具类
 */
public class MyBitmapUtils {

    /**
     * 缩放图片
     */
    public static Bitmap resizeImage(Bitmap bitmap, int width, int height) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        float scaleWidth = ((float) width) / originWidth;
        float scaleHeight = ((float) height) / originHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, originWidth,
                originHeight, matrix, true);
        return resizedBitmap;
    }

    /**
     * 得到绘制文本的图片
     *
     */
    public static Bitmap drawTextToBitmap(Context context, String text) {

        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.record_base_icon);
        Bitmap.Config bitmapConfig = bitmap.getConfig();
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        //新建画布
        Canvas canvas = new Canvas(bitmap);
        //画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(61, 61, 61));
        paint.setTextSize((int) (14 * scale));
        //隐影
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width()) / 2;
        int y = (bitmap.getHeight() + bounds.height()) / 2;
        canvas.drawText(text, x, y, paint);
        return bitmap;
    }

}
