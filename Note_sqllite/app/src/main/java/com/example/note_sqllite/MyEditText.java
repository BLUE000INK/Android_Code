package com.example.note_sqllite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义文本框
 */
public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 插入图片
     *
     */
    public void insertImage(Context context, Bitmap bitmap, String path) {
        ImageSpan span = new ImageSpan(context, bitmap);
        SpannableString ss = new SpannableString(path);
        ss.setSpan(span, 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        append(" ");
        append(ss);
        append(" ");
        append("\n");
        setSelection(this.getText().toString().length());
        setSpanContent(context, this.getText().toString());
    }

    //为文本框设置图文混合
    public void setSpanContent(Context context, String content) {

        setMovementMethod(LinkMovementMethod.getInstance());
        String patternStr = MainActivity.LOCAL_RESOURCE_CATALOG + "\\d*\\.\\w{3}";//数字.字母
        Log.v("TAG","String patternStr="+patternStr);
        Pattern pattern = Pattern.compile(patternStr);
        Matcher m = pattern.matcher(content);
        SpannableString ss = new SpannableString(content);
        while (m.find()) {
            Bitmap bmp = BitmapFactory.decodeFile(m.group());
            ImageSpan imgSpan = new ImageSpan(context, bmp);
            ss.setSpan(imgSpan, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 点击触发事件
            ss.setSpan(new RecordClickPlay(m.group()), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        this.setText(ss);
    }

    //录音图片点击播放

    class RecordClickPlay extends ClickableSpan {
        private String mp3Path;

        public RecordClickPlay(String path) {
            this.mp3Path = path.substring(0, path.length() - 3) + "mp3";
        }


        @Override
        public void onClick(View widget) {
            clearFocus();
            MediaPlayer mPlayer = new MediaPlayer();
            SeekBar seekBar = findViewById(R.id.seekbar);
            Log.v("TAG","SeekBar seekBar");
            //seekBar.setVisibility(VISIBLE);
            Log.v("TAG","录音点击");
            try {
                mPlayer.reset();
                mPlayer.setDataSource(mp3Path);
                Log.v("TAG","mp3Path"+mp3Path);
                mPlayer.prepare();
                mPlayer.start();
                int i = mPlayer.getDuration();
                Log.v("TAG","int i="+i);
                Log.v("TAG","播放成功");

            } catch (IOException e) {
                Log.v("TAG", "播放失败");
            }
        }
    }
}
