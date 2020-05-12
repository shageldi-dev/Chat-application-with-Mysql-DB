package com.example.oguzhanchat;

public class Topar {
    String id;
    String g_name;
    String g_name_id;
    String creater;
    String username;


    public Topar(String id, String g_name, String g_name_id, String creater, String username) {
        this.id = id;
        this.g_name = g_name;
        this.g_name_id = g_name_id;
        this.creater = creater;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_name_id() {
        return g_name_id;
    }

    public void setG_name_id(String g_name_id) {
        this.g_name_id = g_name_id;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
