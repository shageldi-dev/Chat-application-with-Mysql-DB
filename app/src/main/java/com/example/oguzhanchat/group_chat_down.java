package com.example.oguzhanchat;

public class group_chat_down {
    String id;
    String user_id;
    String msg;
    String g_name;
    String date;
    String name;

    public group_chat_down(String id, String user_id, String msg, String g_name, String date,String name) {
        this.id = id;
        this.user_id = user_id;
        this.msg = msg;
        this.g_name = g_name;
        this.date = date;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
