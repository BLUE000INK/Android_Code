package com.example.sharedpreferences_study01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String SharedPreferencesFileName = "config";

    private final static String Name = "name";
    private final static String Num = "num";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取SharedPreferences实例
        preferences = getSharedPreferences(SharedPreferencesFileName,MODE_PRIVATE);
        editor = preferences.edit();
        Button btnWrite = findViewById(R.id.btn_write);
        Button btnRead = findViewById(R.id.btn_read);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(Name,"张文杰");
                editor.putString(Num,"2018011205");
                editor.apply();
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = preferences.getString(Name,null);
                String str_num = preferences.getString(Num,null);
                if(str_name != null && str_num != null)
                    Toast.makeText(MainActivity.this,"学生姓名:"+str_name+"\n学号:"+str_num,Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"无数据",Toast.LENGTH_LONG).show();
            }
        });
    }
}