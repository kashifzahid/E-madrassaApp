package com.example.akhlaqcommunication.emaddrassa.Model;

public class AttendenceModel {
    private String selectedPosition;
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

    public AttendenceModel() {
        selectedPosition = "";
        selected = "";
    }

    public String getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(String selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
