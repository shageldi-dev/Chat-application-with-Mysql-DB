package com.example.oguzhanchat;

public class Degree_Block_B {
    String id;
    String type;
    String level;

    public Degree_Block_B(String id, String type, String level) {
        this.id = id;
        this.type = type;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
