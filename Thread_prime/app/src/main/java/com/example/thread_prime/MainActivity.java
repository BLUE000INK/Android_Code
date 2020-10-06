package com.example.thread_prime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.edittext_in);
        final TextView textView = findViewById(R.id.text_out);

        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
                Log.v("mytag","线程运行");
                    while(!Thread.interrupted()){
                        synchronized (this){
                            try {
                                int num = Integer.parseInt(editText.getText().toString());
                                int result = 1;
                                String str;
                                for (int i = 2; i < num && result == 1; i++) {
                                    if (num % i == 0) {
                                        result = 0;
                                    }
                                }
                                Log.v("mytag", "result=" + result);
                                if(result == 0)
                                    str = "非素数";
                                else
                                    str = "素数";
                                textView.setText(str);
                                Thread.interrupted();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                Log.v("mytag","运算结束");
            }
        };
        Button button = findViewById(R.id.btn_judge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null,myWorker,"WorkThread");
                workThread.start();
            }
        });
    }
}