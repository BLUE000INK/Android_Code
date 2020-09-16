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
    ListView listView = null;
    private final String tag = "mytag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final ArrayList<String> items = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_getmsg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                listView = listView.findViewById(R.id.listview_list);
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.list_view,array_items);
                listView.setAdapter(adapter);
            }
        });
    }
}