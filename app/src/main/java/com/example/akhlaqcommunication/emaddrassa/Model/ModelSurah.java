package com.example.akhlaqcommunication.emaddrassa.Model;

public class ModelSurah {


    String surah_name,surah_number,surah_id;
    int surah_ayat;

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



}
