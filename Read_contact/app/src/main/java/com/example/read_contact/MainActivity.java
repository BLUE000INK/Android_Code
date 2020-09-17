package com.example.read_contact;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private String[] datas = {"暂未读取联系人"};
    private ListView listView;
    private final String tag = "mytag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_getmsg);

        listView = (ListView)findViewById(R.id.listview_list);
        adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,datas);
        listView.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(tag,"按钮点击");
                ArrayList<String> items = new ArrayList<>();
                Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
                while (cursor.moveToNext()){

                    String msg;

                    String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    msg="id:"+id;

                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    msg= msg+"name:"+name;

                    Log.v(tag,msg);

                    items.add(msg);
                }
                Log.v(tag,items.get(0).toString());
                String [] array_items = new String[items.size()];
                items.toArray(array_items);
                //刷新Adapter
                adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_expandable_list_item_1,array_items);
                listView.setAdapter(adapter);
            }
        });
    }
}