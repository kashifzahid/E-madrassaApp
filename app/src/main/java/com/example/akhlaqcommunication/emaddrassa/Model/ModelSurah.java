package com.example.akhlaqcommunication.emaddrassa.Model;

public class ModelSurah {


    String surah_name,surah_number,surah_id;
    int surah_ayat;
    int surah_ayat_start;
    int surah_ayat_end;
    String para_id;
    String para_name;
    String surah_from;

    public String getSurah_from() {
        return surah_from;
    }

    public void setSurah_from(String surah_from) {
        this.surah_from = surah_from;
    }

    public String getSurah_to() {
        return surah_to;
    }

    public void setSurah_to(String surah_to) {
        this.surah_to = surah_to;
    }

    String surah_to;

    public String getPara_no() {
        return para_no;
    }

    public void setPara_no(String para_no) {
        this.para_no = para_no;
    }

    String para_no;


    public ModelSurah(String para_id, String para_name,String para_no) {
        this.para_id = para_id;
        this.para_name = para_name;
        this.para_no=para_no;
    }

    public int getSurah_ayat_start() {
        return surah_ayat_start;
    }

    public void setSurah_ayat_start(int surah_ayat_start) {
        this.surah_ayat_start = surah_ayat_start;
    }

    public int getSurah_ayat_end() {
        return surah_ayat_end;
    }

    public void setSurah_ayat_end(int surah_ayat_end) {
        this.surah_ayat_end = surah_ayat_end;
    }

    public String getPara_id() {
        return para_id;
    }

    public void setPara_id(String para_id) {
        this.para_id = para_id;
    }

    public String getPara_name() {
        return para_name;
    }

    public void setPara_name(String para_name) {
        this.para_name = para_name;
    }

    public String getSurah_name() {
        return surah_name;
    }

    public void setSurah_name(String surah_name) {
        this.surah_name = surah_name;
    }

    public String getSurah_number() {
        return surah_number;
    }

    public void setSurah_number(String surah_number) {
        this.surah_number = surah_number;
    }

    public String getSurah_id() {
        return surah_id;
    }

    public void setSurah_id(String surah_id) {
        this.surah_id = surah_id;
    }

    public int getSurah_ayat() {
        return surah_ayat;
    }

    public void setSurah_ayat(int surah_ayat) {
        this.surah_ayat = surah_ayat;
    }

    public ModelSurah(String name, String num, String id, int ayat) {
    this.surah_name=name;
    this.surah_number=num;
    this.surah_id=id;
    this.surah_ayat=ayat;


    }
    public ModelSurah(String name, String num, int start,int end){
        this.surah_name=name;
        this.surah_number=num;

        this.surah_ayat_start=start;
        this.surah_ayat_end=end;
    }



}
