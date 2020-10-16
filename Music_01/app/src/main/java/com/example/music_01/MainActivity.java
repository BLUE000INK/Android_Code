package com.example.music_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mytag";
    private MediaPlayer mediaPlayer;
    ListView mylist;
    List<Song> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = findViewById(R.id.list_music);
        list = new ArrayList<>();
        list = Song_make.getmusic(this);
        List_adapter list_adapter = new List_adapter(this, list);
        mylist.setAdapter(list_adapter);
    }
        /*mediaPlayer = new MediaPlayer();

        final Button btn_last = findViewById(R.id.btn_last);
        final Button btn_next = findViewById(R.id.btn_next);
        final Button btn_start = findViewById(R.id.btn_start);
        final Button btn_pause = findViewById(R.id.btn_pause);

        btn_pause.setEnabled(false);
        btn_next.setEnabled(false);
        btn_last.setEnabled(false);
        //开始播放
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    mediaPlayer.reset();
                    Log.v(TAG,"reset");
                    AssetManager assetManager = getAssets();
                    AssetFileDescriptor assetFileDescriptor = assetManager.openFd("非正常励志歌 - 齐一.mp3");
                    Log.v(TAG,"文件打开成功");
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Log.v(TAG,"start");

                    btn_start.setEnabled(false);
                    btn_pause.setEnabled(true);
                    btn_last.setEnabled(true);
                    btn_next.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //暂停播放
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btn_pause.setText("Play");
                }else {
                    mediaPlayer.start();
                    btn_pause.setText("Pause");
                }

            }
        });
    }*/
    class List_adapter extends BaseAdapter{
        Context context;
        List<Song>list;
        public List_adapter(MainActivity mainActivity,List<Song>list){
            this.context = mainActivity;
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Myholder myholder;
            if(convertView == null){
                myholder = new Myholder();
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_text,null);
                myholder.t_position = convertView.findViewById(R.id.t_postion);
                myholder.t_song = convertView.findViewById(R.id.t_song);
                myholder.t_singer = convertView.findViewById(R.id.t_singer);
                myholder.t_duration = convertView.findViewById(R.id.t_duration);
                convertView.setTag(myholder);
            }else {
                myholder = (Myholder) convertView.getTag();
            }
            myholder.t_song.setText(list.get(position).song.toString());
            myholder.t_singer.setText(list.get(position).singer.toString());
            String time = Song_make.formatTime(list.get(position).duration);

            myholder.t_duration.setText(time);
            myholder.t_position.setText(position + 1 + "");

            return convertView;
        }
        class Myholder{
            TextView t_position,t_song,t_singer,t_duration;
        }
    }


}