package com.example.akhlaqcommunication.emaddrassa.RecyclerClasses;

public class attendence_modelclass {

    int profile_image;
    String student_name;
    String student_roll_number;
    String student_semster;
    String student_class;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    String student_id;
    String radio_present,radio_absent,radio_late;

    private int selectedPosition;
    private String selected;
    private boolean op1Sel,op2Sel,op3Sel;

    public boolean isOp1Sel() {
        return op1Sel;
    }

    public void setOp1Sel(boolean op1Sel) {
        this.op1Sel = op1Sel;
        if(op1Sel){ // To make sure only one option is selected at a time
            setOp2Sel(false);
            setOp3Sel(false);
        }
    }

    public boolean isOp2Sel() {
        return op2Sel;
    }

    public void setOp2Sel(boolean op2Sel) {
        this.op2Sel = op2Sel;
        if(op2Sel){
            setOp1Sel(false);
            setOp3Sel(false);
        }
    }

    public boolean isOp3Sel() {
        return op3Sel;
    }

    public void setOp3Sel(boolean op3Sel) {
        this.op3Sel = op3Sel;
        if(op3Sel){
            setOp2Sel(false);
            setOp1Sel(false);
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public attendence_modelclass(String student_id, int profile_image, String student_name, String student_roll_number, String student_semster, String student_class) {
        this.student_id = student_id;
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
