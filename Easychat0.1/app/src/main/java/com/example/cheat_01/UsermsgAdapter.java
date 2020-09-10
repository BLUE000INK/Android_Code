package com.example.cheat_01;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsermsgAdapter extends RecyclerView.Adapter <UsermsgAdapter.UsermsgViewHolder> {
    private final List<Usermsg> mUserWordMsgList;
    private String Tag = "My_Log";

    UsermsgAdapter(List<Usermsg> userWordMsgList){
        mUserWordMsgList = userWordMsgList;
    }

    @Override
    public int getItemViewType(int position) {
        Usermsg msg = mUserWordMsgList.get(position);
        return msg.getType();
    }

    @NonNull
    @Override
    public UsermsgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == Usermsg.Type_receive){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_msg,parent,false);
            return new UsermsgViewHolder(view);
        }else {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_msg,parent,false);
            return new UsermsgViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UsermsgViewHolder holder, int position) {
        Usermsg msg = mUserWordMsgList.get(position);
        if(msg.getType() == Usermsg.Type_receive){
            Log.v(Tag,"onBindViewHolder+tvleft"+msg.getText());
            holder.tvLeft.setText(msg.getText());
        }else {
            holder.tvRight.setText(msg.getText());
            Log.v(Tag,"onBindViewHolder+tvright"+msg.getText());
        }

    }

    @Override
    public int getItemCount() {
        return mUserWordMsgList.size();
    }

    class UsermsgViewHolder extends RecyclerView.ViewHolder{
        TextView tvLeft,tvRight;

        UsermsgViewHolder(@NonNull View itemView){
            super(itemView);
            tvLeft = itemView.findViewById(R.id.left_msg);
            tvRight = itemView.findViewById(R.id.right_msg);
        }
    }
}
