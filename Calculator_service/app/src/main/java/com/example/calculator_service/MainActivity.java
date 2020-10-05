package com.example.calculator_service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Provider;

public class MainActivity extends AppCompatActivity {
    MyService myService = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.v("mytag","onServiceConnected");
                myService=((MyService.LocalBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.v("mytag","onServiceDisconnected");
            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = findViewById(R.id.btn_start);
        Button btn_cal = findViewById(R.id.btn_cal);
        Button btn_stop = findViewById(R.id.btn_stop);
        final TextView textView = findViewById(R.id.text_result);
        final EditText editText = findViewById(R.id.edittext_str);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
                Log.v("mytag","calculate:"+editText.getText().toString());

            }
        });

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myService != null){
                    Log.v("mytag","result="+myService.calculate(editText.getText().toString()));
                    textView.setText(myService.calculate(editText.getText().toString()));
                }else
                    Log.v("mytag","计算服务未开启");
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               unbindService(serviceConnection);
            }
        });
    }
}