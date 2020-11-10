package com.example.note_anything01;

import android.provider.BaseColumns;

public class Notes {
    public Notes(){

    }
    public static class Note implements BaseColumns{
        public static final String TABLE_NAME="notes";
        public static final String COLUMN_NAME_TITLE="title";//列：标题
        public static final String COLUMN_NAME_TIME = "2020.01.01";//字段：时间
        public static final String COLUMN_NAME_CONTENT = "content";//字段：正文
        public static final String COLUMN_NAME_PATH_JPG = "0";//字段：图片地址
        public static final String COLUMN_NAME_PATH_MP3 = "0";//字段：录音地址
    }
}
