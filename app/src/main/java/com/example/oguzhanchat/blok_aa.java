package com.example.oguzhanchat;

public class blok_aa {
    String id;
    String object;
    String subject;
    String subject_id;

    public blok_aa(String id, String object, String subject, String subject_id) {
        this.id = id;
        this.object = object;
        this.subject = subject;
        this.subject_id = subject_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
}
