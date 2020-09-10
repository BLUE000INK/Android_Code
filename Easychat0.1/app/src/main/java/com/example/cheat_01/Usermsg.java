package com.example.cheat_01;

public class Usermsg {
    public static final int Type_send = 1;
    public static final int Type_receive = 0;

    private String text;
    private int type;

    public Usermsg(String text,int type){
        this.text = text;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
