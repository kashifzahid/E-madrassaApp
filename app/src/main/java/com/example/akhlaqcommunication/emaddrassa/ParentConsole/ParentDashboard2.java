package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.akhlaqcommunication.emaddrassa.About;
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.TeacherDashboard;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ParentDashboard2 extends AppCompatActivity {
    private SharedPreferenceEdit sharedPreferenceEdit;
    private String parent_id,student_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_dashboard2);
        sharedPreferenceEdit=new SharedPreferenceEdit(ParentDashboard2.this);

        String status=sharedPreferenceEdit.GetStudentStatus();
        parent_id=sharedPreferenceEdit.GetDriverId();
        if(status.equals("login")){

            student_id=sharedPreferenceEdit.GetStudentId();
            getDashboard(student_id);
        }else{
            getStudentId();
        }




    }

    private void getDashboard(String id) {

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","ParentDashboard");
            Log.e("tag", "getDashboard: "+id );
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        VolleyRequest.PostRequest(ParentDashboard2.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {

                    String absents=jsonObject.getString("absent_count");
                    String attends=jsonObject.getString("present_count");

//
//                    absent.setText(absents);
//                    present.setText(attends);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void OnFailure(String err) {

            }
        });


    }

    private void getStudentId() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","ParentStudent");
            jsonObject.put("id",parent_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        VolleyRequest.PostRequest(ParentDashboard2.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    student_id=jsonObject.getString("id");
                    Log.e("tag", "OnSuccess: "+student_id );


                    sharedPreferenceEdit.AddStudentId(student_id);
                    getDashboard(student_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void OnFailure(String err) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ParentDashboard2.super.onBackPressed();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }









    public void openAbout(View view) {
        Log.e("yes", "openAbout: " );
        Intent opendiary = new Intent(getApplicationContext(), About.class);
        startActivity(opendiary);
    }

    public void OpenClass(View view) {
    }

    public void openAttendence(View view) {
        Intent opendiary = new Intent(getApplicationContext(), Parent_attendence.class);
        startActivity(opendiary);
    }

    public void openDiary(View view) {
        Intent opendiary = new Intent(getApplicationContext(), ParentDailyDiaryItems.class);
        startActivity(opendiary);
    }
}
