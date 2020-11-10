package com.example.note_anything01;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    NotesDBHelper mDbHelper;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertDialog();
            }
        });

        list = findViewById(R.id.list_notes);

        mDbHelper = new NotesDBHelper(this);
        refreshNotesList(list);
    }

    private void refreshNotesList(ListView list) {
        if (mDbHelper != null) {
            Log.v("TAG","mDbHelper不为空");
            ArrayList<Map<String,String>> items = getAllNotes();
            Log.v("TAG","items获取");
            SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.list_item,

                    new String[]{Notes.Note._ID, Notes.Note.COLUMN_NAME_TITLE, Notes.Note.COLUMN_NAME_CONTENT},

                    new int[]{R.id.list_id, R.id.list_title,R.id.list_content});
            Log.v("TAG","配置Adapter");
            list.setAdapter(adapter);
            this.registerForContextMenu(list);
        }else{
            Log.v("TAG","mDbHelper为空");
            Toast.makeText(this,"Not found", Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<Map<String, String>> getAllNotes() {
        if(mDbHelper == null){
            Log.v("TAG","mDbHelper == null");
            return null;
        }
        Log.v("TAG","获取SQLiteDatabase db");
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] box = {
                Notes.Note._ID,
                Notes.Note.COLUMN_NAME_TITLE,
                Notes.Note.COLUMN_NAME_CONTENT,
        };
        //排序
        String sortOrder = Notes.Note.COLUMN_NAME_TITLE+" DESC";
        Log.v("TAG","获取Cursor c");
        Cursor c = db.query(
                Notes.Note.TABLE_NAME,
                box,
                null,
                null,
                null,
                null,
                sortOrder
        );
        Log.v("TAG","Cursor c");
        return Cursor_NoteList(c);
    }

    private ArrayList<Map<String, String>> Cursor_NoteList(Cursor c) {
        ArrayList<Map<String,String>> result = new ArrayList<>();
        while(c.moveToNext()){
            Map<String,String>map = new HashMap<>();
            map.put(Notes.Note._ID,String.valueOf(c.getString(c.getColumnIndex(Notes.Note._ID))));
            map.put(Notes.Note.COLUMN_NAME_TITLE,c.getString(c.getColumnIndex(Notes.Note.COLUMN_NAME_TITLE)));
            Log.v("TAG","WordName="+c.getString(c.getColumnIndex(Notes.Note.COLUMN_NAME_TITLE)));
            map.put(Notes.Note.COLUMN_NAME_CONTENT,c.getString(c.getColumnIndex(Notes.Note.COLUMN_NAME_CONTENT)));

            result.add(map);
        }
        Log.v("TAG","result");

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String sql = "insert into  words(_id,word,meaning,sample) values(?,?,?,?)";

        Log.v("TAG","插入成功");
        return result;
    }

    //新增对话框
    private void InsertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View linearLayout = View.inflate(this,R.layout.new_note,null);
        dialog.setView(linearLayout);
        dialog.show();
        final String title = ((EditText) linearLayout.findViewById(R.id.title)).getText().toString();
        final String content = ((EditText) linearLayout.findViewById(R.id.content)).getText().toString();

        FloatingActionButton fab1 = findViewById(R.id.Float_finish);
        FloatingActionButton fab_jpg = findViewById(R.id.Float_camera);
        FloatingActionButton fab_mp3 = findViewById(R.id.Float_audio);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.equals(null)){
                    Toast.makeText(view.getContext(),"标题不能为空",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Insert(title,content);
                    dialog.dismiss();
                }

            }
        });
    }

    private void Insert(String title, String content) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Notes.Note._ID,getGUID());
        values.put(Notes.Note.COLUMN_NAME_TITLE,title);
        values.put(Notes.Note.COLUMN_NAME_CONTENT,content);
        long newRowId;
        newRowId = db.insert(
                Notes.Note.TABLE_NAME,
                null,
                values);
    }

    private String getGUID() {
        // 创建 GUID 对象
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String a = uuid.toString();
        // 转换为大写
        a = a.toUpperCase();
        // 替换 -
        a = a.replaceAll("-", "");
        return a;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}