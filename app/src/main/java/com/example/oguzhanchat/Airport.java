package com.example.oguzhanchat;

public class Airport {
    String at;
    String familya;
    String id;
    String username;
    String degree;
    String faculty;
    String department;
    int imgSrc;
    int status;
    String level;




    public Airport(String id, String at, String familya, String username, String degree, String faculty, String department, int imgSrc, int status,String level) {
        this.id=id;
        this.at = at;
        this.familya = familya;
        this.username = username;
        this.degree = degree;
        this.faculty = faculty;
        this.department = department;
        this.imgSrc = imgSrc;
        this.status = status;
        this.level=level;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


}
