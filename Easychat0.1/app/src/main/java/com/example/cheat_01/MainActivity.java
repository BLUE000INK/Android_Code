package com.example.cheat_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private UsermsgAdapter mUserWorldMsgAdapter;
    private EditText mEditTextUserWordMsg;
    private List<Usermsg> mUserWorldMsgList;
    private static final String TAG= "My_Log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextUserWordMsg = findViewById(R.id.edittext_user_msg);
        mUserWorldMsgList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerview);
        mUserWorldMsgAdapter = new UsermsgAdapter(mUserWorldMsgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mUserWorldMsgAdapter);

    }

    public void sendClick(View view) {
        String content = mEditTextUserWordMsg.getText().toString();
        content = content.trim();
        if(content.length() == 0){
            Toast.makeText(this,"文本内容不能为空",Toast.LENGTH_SHORT).show();
            return;
        }


        addSendMsg(content);

        addReceiveMsg("<"+content+"> is good!");
    }

    private void addReceiveMsg(String content) {
        Usermsg userWordMsg = new Usermsg(content,Usermsg.Type_receive);
        mUserWorldMsgList.add(userWordMsg);

        mUserWorldMsgAdapter.notifyItemInserted(mUserWorldMsgList.size()-1);
        mRecyclerView.scrollToPosition(mUserWorldMsgList.size()-1);

        mEditTextUserWordMsg.setText("");
    }

    private void addSendMsg(String content) {

        Log.v(TAG,"addSendMsg+"+content);

        Usermsg userWorldmsg = new Usermsg(content,Usermsg.Type_send);

        mUserWorldMsgList.add(userWorldmsg);

        mUserWorldMsgAdapter.notifyItemInserted(mUserWorldMsgList.size()-1);
        mRecyclerView.scrollToPosition(mUserWorldMsgList.size()-1);
        mEditTextUserWordMsg.setText("");

    }
}