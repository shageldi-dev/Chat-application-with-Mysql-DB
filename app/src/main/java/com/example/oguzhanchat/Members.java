package com.example.oguzhanchat;

public class Members {
    String at;
    String familya;
    String id;
    String username;
    String degree;
    String faculty;
    String department;
    String level;
    String status;

    public Members(String at, String familya, String id, String username, String degree, String faculty, String department, String level, String status) {
        this.at = at;
        this.familya = familya;
        this.id = id;
        this.username = username;
        this.degree = degree;
        this.faculty = faculty;
        this.department = department;
        this.level = level;
        this.status = status;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getFamilya() {
        return familya;
    }

    public void setFamilya(String familya) {
        this.familya = familya;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
