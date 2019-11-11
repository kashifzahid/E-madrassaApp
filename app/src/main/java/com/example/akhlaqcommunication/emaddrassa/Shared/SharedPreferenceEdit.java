package com.example.akhlaqcommunication.emaddrassa.Shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static com.android.volley.VolleyLog.TAG;

public class SharedPreferenceEdit {
    private Context context;



    public SharedPreferenceEdit(Context context) {
        this.context=context;

    }

   public void AddDriverId(String id,String type){
       SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = prefs.edit();
       editor.putString("LOGIN_STATUS", "login");
       editor.putString("LOGIN_TYPE", type);
       editor.putString("DRIVER_ID",id);


       editor.apply();
    }
    public void AddStudentId(String id){
        SharedPreferences prefs = context.getSharedPreferences("STUDENT_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("STUDENT_STATUS", "login");
        editor.putString("STUDENT_ID",id);


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
    public String GetLoginType() {
        SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
        String logintype = prefs.getString("LOGIN_TYPE", "");
        return logintype;
    }
    public String GetClassStatus() {
        SharedPreferences prefs = context.getSharedPreferences("CLASS_PREF", Context.MODE_PRIVATE);
        String classStatus = prefs.getString("CLASS_STATUS", "");
        return classStatus;
    }
    public String GetStudentStatus() {
        SharedPreferences prefs = context.getSharedPreferences("STUDENT_PREF", Context.MODE_PRIVATE);
        String classStatus = prefs.getString("STUDENT_STATUS", "");
        return classStatus;
    }
    public String GetDriverId() {
        SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
        String DriverId = prefs.getString("DRIVER_ID", "");
        return DriverId;
    }
    public String GetStudentId() {
        SharedPreferences prefs = context.getSharedPreferences("STUDENT_PREF", Context.MODE_PRIVATE);
        String DriverId = prefs.getString("STUDENT_ID", "");
        return DriverId;
    }
    public String GetClassId() {
        SharedPreferences prefs = context.getSharedPreferences("CLASS_PREF", Context.MODE_PRIVATE);
        String classStatus = prefs.getString("CLASS_ID", "");
        Log.e(TAG, "GetClassId: "+classStatus );
        return classStatus;
    }
}
