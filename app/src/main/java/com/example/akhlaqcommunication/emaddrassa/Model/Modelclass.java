package com.example.akhlaqcommunication.emaddrassa.Model;

public class Modelclass {

    int profile_image;
    String student_name;
    String student_roll_number;
    String student_semster;
    String student_class;
    String id;
    String date,status,teacher,remarks,grade;

    public Modelclass(String id,String date,  String teacher, String remarks, String grade) {
        this.id=id;
        this.date = date;
        this.teacher = teacher;
        this.remarks = remarks;
        this.grade = grade;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Modelclass(String date, String status) {
        this.date = date;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Modelclass(String id,int profile_image, String student_name, String student_roll_number, String student_semster, String student_class) {
        this.profile_image = profile_image;
        this.student_name = student_name;
        this.student_roll_number = student_roll_number;
        this.student_semster = student_semster;
        this.student_class = student_class;
        this.id=id;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_roll_number() {
        return student_roll_number;
    }

    public String getStudent_semster() {
        return student_semster;
    }

    public String getStudent_class() {
        return student_class;
    }

}
