package com.example.music_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private int Num = 0;
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
        Log.v(TAG,"list");

        final Button btn_start = findViewById(R.id.btn_start);
        final Button btn_pause = findViewById(R.id.btn_pause);
        final Button btn_next = findViewById(R.id.btn_next);
        final Button btn_last = findViewById(R.id.btn_last);
        final Button btn_loop = findViewById(R.id.btn_loop);

        List_adapter list_adapter = new List_adapter(this, list);
        mylist.setAdapter(list_adapter);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String p = list.get(position).path;//获得歌曲的地址
                Num = position;
                Log.v(TAG,"p="+p);
                play(p);
                btn_pause.setText("Pause");
            }
        });



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                btn_pause.setText("Play");
                btn_start.setVisibility(View.INVISIBLE);
                btn_pause.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.VISIBLE);
                btn_last.setVisibility(View.VISIBLE);
                btn_loop.setVisibility(View.VISIBLE);
                mylist.setVisibility(View.VISIBLE);
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    btn_pause.setText("Play");
                    mediaPlayer.pause();
                }else{
                    btn_pause.setText("Pause");
                    String p = list.get(Num).path;//获得歌曲的地址
                    play(p);
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Num == list.size() - 1){
                    Num = 0;
                }else{
                    Num++;
                }
                String p = list.get(Num).path;
                play(p);
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Num="+Num);
                Log.v(TAG,"list.size="+list.size());
                if(Num == 0){
                    Num = 0;
                }else{
                    Num--;
                }
                String p = list.get(Num).path;
                play(p);
            }
        });
    }
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


    public void play(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}