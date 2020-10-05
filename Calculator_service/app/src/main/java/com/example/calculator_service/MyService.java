package com.example.calculator_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private LocalBinder myBinder = new LocalBinder();
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.v("mytag","onCreat");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.v("mytag","onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Calculator_base calculator = new Calculator_base();
        try{
            String str = intent.getStringExtra("str");
            Calculator_base calculator_base = new Calculator_base();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public class LocalBinder extends Binder {
        MyService getService(){
            Log.v("mytag","LoacalBinder()");
            return MyService.this;
        }
    }

    public String calculate(String str){
        Log.v("mytag","calculate--"+str);
        Calculator_base calculator = new Calculator_base();
        try{
           return calculator.caculate(str)+"";
        } catch (Exception e) {
            return "算式错误,计算失败";
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("mytag","onBinder()");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("mytag","onUnbind()");
        return super.onUnbind(intent);
    }
}
