package com.example.calculator_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        Toast.makeText(this,"计算服务开始运行",Toast.LENGTH_LONG);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"计算服务停止运行",Toast.LENGTH_LONG);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Calculator_base calculator = new Calculator_base();
        try{
            String str = intent.getStringExtra("str");
            Calculator_base calculator_base = new Calculator_base();
            Toast.makeText(this,"计算结果为："+calculator_base.caculate(str),Toast.LENGTH_LONG);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"算式错误！",Toast.LENGTH_LONG);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
