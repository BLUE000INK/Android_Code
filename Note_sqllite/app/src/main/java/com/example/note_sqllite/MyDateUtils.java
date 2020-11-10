package com.example.note_sqllite;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理日期的工具类
 */
public class MyDateUtils {

    /**
     * 转换显示在列表界面
     *
     */
    public static String convertForList(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        return dateFormat.format(date);
    }

    /**
     * 转换显示在编辑界面
     *
     */
    public static String convertForEdit(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
        return dateFormat.format(date);
    }
}
