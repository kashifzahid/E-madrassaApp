package com.example.akhlaqcommunication.emaddrassa.Shared;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceEdit {
    private Context context;



    public SharedPreferenceEdit(Context context) {
        this.context=context;

    }

   public void AddDriverId(String id){
       SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = prefs.edit();
       editor.putString("LOGIN_STATUS", "login");
       editor.putString("DRIVER_ID",id);


       editor.apply();
    }
    public void AddClassId(String id){
        SharedPreferences prefs = context.getSharedPreferences("CLASS_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("CLASS_STATUS", "login");
        editor.putString("CLASS_ID",id);


        editor.apply();
    }
    public String GetLoginStatus() {
        SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
        String loginStatus = prefs.getString("LOGIN_STATUS", "");
        return loginStatus;
    }
    public String GetClassStatus() {
        SharedPreferences prefs = context.getSharedPreferences("CLASS_PREF", Context.MODE_PRIVATE);
        String classStatus = prefs.getString("CLASS_STATUS", "");
        return classStatus;
    }
    public String GetDriverId() {
        SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
        String DriverId = prefs.getString("DRIVER_ID", "");
        return DriverId;
    }
    public String GetClassId() {
        SharedPreferences prefs = context.getSharedPreferences("CLASS_PREF", Context.MODE_PRIVATE);
        String classStatus = prefs.getString("CLASS_ID", "");
        return classStatus;
    }
}
