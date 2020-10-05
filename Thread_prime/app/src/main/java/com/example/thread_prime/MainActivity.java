package com.example.thread_prime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Runnable myWOrker = new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis() + 10 * 1000;

                while(System.currentTimeMillis()<endTime)
                    while(!Thread.interrupted()){
                        synchronized (this){
                            try{
                                Log.v("mytag","运算中");
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            }
        };
    }
}