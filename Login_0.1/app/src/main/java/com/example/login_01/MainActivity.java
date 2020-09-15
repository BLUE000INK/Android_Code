package com.example.login_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn_tips);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("UserId:abc\nPassword:123");
                builder.setTitle("Tips");
                builder.setPositiveButton("确定",null);
                builder.show();
            }
        });
        Button btn1 = findViewById(R.id.btn_login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final View viewDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_login,null,false);
                builder.setTitle("登录对话框")
                        .setView(viewDialog)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText editText_Id = viewDialog.findViewById(R.id.edittext_user_id);
                                EditText editText_Pwd = viewDialog.findViewById(R.id.edittext_user_password);
                                if(editText_Id.getText().toString().equals("abc")&&editText_Pwd.getText().toString().equals("123"))
                                {
                                    Toast.makeText(MainActivity.this,"信息正确,登录成功！",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this,"信息错误,登录失败！",Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("取消",null);
                builder.create().show();
            }
        });
    }
}