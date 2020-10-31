package com.example.english_words01;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    WordsDBHelper mDbHelper;
    private ListView list;
    private final static String TAG = "mytag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //新增单词
                InsertDialog();
            }
        });


        //listView注册上下文菜单
        list = findViewById(R.id.list_words);
        //registerForContextMenu(list);
        //更新单词列表
        mDbHelper = new WordsDBHelper(this);
        Log.v(TAG,"更新列表前");
        refreshWordsList(list);
    }

    //新增对话框
    private void InsertDialog() {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.insert, null);
        new AlertDialog.Builder(this)
                .setTitle("新增单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strWord = ((EditText) tableLayout.findViewById(R.id.txtWord)).getText().toString();
                        String strMeaning = ((EditText) tableLayout.findViewById(R.id.txtMeaning)).getText().toString();
                        String strSample = ((EditText) tableLayout.findViewById(R.id.txtSample)).getText().toString();

                        //既可以使用Sql语句插入，也可以使用使用insert方法插入
                        // InsertUserSql(strWord, strMeaning, strSample);
                        Insert(strWord, strMeaning, strSample);

                        //单词已经插入到数据库，更新显示列表
                        refreshWordsList(list);


                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框
    }

    //删除对话框
    private void DeleteDialog(final String strId) {
        new AlertDialog.Builder(this).setTitle("删除单词").setMessage("是否真的删除单词?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //既可以使用Sql语句删除，也可以使用使用delete方法删除
                Delete(strId);

                //单词已经删除，更新显示列表
                refreshWordsList(list);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create().show();
    }


    private void refreshWordsList(ListView list) {
        if (mDbHelper != null) {
            Log.v(TAG,"mDbHelper不为空");
            ArrayList<Map<String,String>> items = getAllWords();
            Log.v(TAG,"items获取");
            SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.words_items,

                    new String[]{Words.Word._ID, Words.Word.COLUMN_NAME_WORD, Words.Word.COLUMN_NAME_MEANING, Words.Word.COLUMN_NAME_SAMPLE},
                    new int[]{R.id.text_id, R.id.text_words,R.id.text_notes,R.id.text_eg});
            Log.v(TAG,"配置Adapter");
            list.setAdapter(adapter);
        }else{
            Log.v(TAG,"mDbHelper为空");
            Toast.makeText(this,"Not found", Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<Map<String, String>> getAllWords() {
        if(mDbHelper == null){
            Log.v(TAG,"mDbHelper == null");
            return null;
        }
        Log.v(TAG,"获取SQLiteDatabase db");
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] box = {
                Words.Word._ID,
                Words.Word.COLUMN_NAME_WORD,
                Words.Word.COLUMN_NAME_MEANING,
                Words.Word.COLUMN_NAME_SAMPLE
        };
        //排序
        String sortOrder = Words.Word.COLUMN_NAME_WORD+" DESC";
        Log.v(TAG,"获取Cursor c");
        Cursor c = db.query(
                Words.Word.TABLE_NAME,
                box,
                null,
                null,
                null,
                null,
                sortOrder
        );
        Log.v(TAG,"Cursor c");
        return Cursor_WordList(c);
    }

    private ArrayList<Map<String, String>> Cursor_WordList(Cursor c) {
        ArrayList<Map<String,String>> result = new ArrayList<>();
        while(c.moveToNext()){
            Map<String,String>map = new HashMap<>();
            map.put(Words.Word._ID,String.valueOf(c.getString(c.getColumnIndex(Words.Word._ID))));
            map.put(Words.Word.COLUMN_NAME_WORD,c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_WORD)));
            Log.v(TAG,"WordName="+c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_WORD)));
            map.put(Words.Word.COLUMN_NAME_MEANING,c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_MEANING)));
            map.put(Words.Word.COLUMN_NAME_SAMPLE,c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE)));
            result.add(map);
        }
        Log.v(TAG,"result");

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String sql = "insert into  words(_id,word,meaning,sample) values(?,?,?,?)";

        Log.v(TAG,"插入成功");
        return result;
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
        if (id == R.id.action_A) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getGUID(){
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
    
    public void Insert(String strWord, String strMeaning, String strSample) {

        //Gets the data repository in write mode*/
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Words.Word._ID, getGUID());
        values.put(Words.Word.COLUMN_NAME_WORD, strWord);
        values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
        values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                Words.Word.TABLE_NAME,
                null,
                values);
    }

    public void Delete(String strId){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // 定义where子句
        String selection = Words.Word._ID + " = ?";

        // 指定占位符对应的实际参数
        String[] selectionArgs = {strId};

        // Issue SQL statement.
        db.delete(Words.Word.TABLE_NAME, selection, selectionArgs);
    }

    public void Update(String strId, String strWord, String strMeaning, String strSample) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Words.Word.COLUMN_NAME_WORD, strWord);
        values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
        values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

        String selection = Words.Word._ID + " = ?";
        String[] selectionArgs = {strId};

        int count = db.update(
                Words.Word.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    
    //关闭数据库
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDbHelper.close();
    }
}