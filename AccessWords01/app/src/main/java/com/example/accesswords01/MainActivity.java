package com.example.accesswords01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView list;
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = this.getContentResolver();
        Button btn_all = findViewById(R.id.btn_all);
        Button btn_delete = findViewById(R.id.btn_delete);
        list = findViewById(R.id.list_words);

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Map<String,String>> result = new ArrayList<>();
                Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI,new String[] { UserDictionary.Words._ID},null,null,null);
                if(cursor == null){
                    Toast.makeText(MainActivity.this,"未找到记录",Toast.LENGTH_LONG).show();
                    return;
                }
                if(cursor.moveToFirst()){
                    Map<String,String> map = new HashMap<>();
                    map.put(UserDictionary.Words._ID,String.valueOf(cursor.getString(cursor.getColumnIndex(UserDictionary.Words._ID))));
                    Log.v("TAG","cursor:"+cursor.getColumnIndex(UserDictionary.Words._ID));
                    result.add(map);
                }
                refreshWordsList(result);

            }
        });

    }
    private void refreshWordsList(ArrayList<Map<String,String>> result) {
        ArrayList<Map<String,String>> items = result;
        SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.list_item,

                new String[]{UserDictionary.Words._ID},
                new int[]{R.id.text_words});
                list.setAdapter(adapter);
    }
}