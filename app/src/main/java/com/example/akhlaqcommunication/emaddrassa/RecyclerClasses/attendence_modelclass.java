package com.example.akhlaqcommunication.emaddrassa.RecyclerClasses;

public class attendence_modelclass {

    int profile_image;
    String student_name,student_roll_number,student_semster,student_class;
    String radio_present,radio_absent,radio_late;

    public attendence_modelclass(int profile_image, String student_name, String student_roll_number, String student_semster, String student_class, String radio_present, String radio_absent, String radio_late) {
        this.profile_image = profile_image;
        this.student_name = student_name;
        this.student_roll_number = student_roll_number;
        this.student_semster = student_semster;
        this.student_class = student_class;
        this.radio_present = radio_present;
        this.radio_absent = radio_absent;
        this.radio_late = radio_late;
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

    public String getRadio_present() {
        return radio_present;
    }

    public String getRadio_absent() {
        return radio_absent;
    }

    public String getRadio_late() {
        return radio_late;
    }
}
