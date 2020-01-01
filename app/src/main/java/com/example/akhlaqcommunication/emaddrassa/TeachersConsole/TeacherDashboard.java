package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

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
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class TeacherDashboard extends AppCompatActivity {
    private SharedPreferenceEdit sharedPreferenceEdit;
    private String teacher_id,class_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        sharedPreferenceEdit=new SharedPreferenceEdit(TeacherDashboard.this);
        String status=sharedPreferenceEdit.GetClassStatus();
        teacher_id=sharedPreferenceEdit.GetDriverId();
        if(status.equals("login")){

            class_id=sharedPreferenceEdit.GetClassId();
            getDashboard(class_id);
        }else{
            getClassId();
        }


    }

    private void getDashboard(String id) {

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","TeacherDashboard");
            Log.e("tag", "getDashboard: "+class_id );
            jsonObject.put("id",class_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        VolleyRequest.PostRequest(TeacherDashboard.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    String totals=jsonObject.getString("total");
                    String absents=jsonObject.getString("absent");
                    String attends=jsonObject.getString("attend");

//                    total.setText(totals);
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

    private void getClassId() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","TeacherClass");
            jsonObject.put("id",teacher_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        VolleyRequest.PostRequest(TeacherDashboard.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    class_id=jsonObject.getString("ClassId");
                    Log.e("tag", "OnSuccess: "+class_id );


                    sharedPreferenceEdit.AddClassId(class_id);
                    getDashboard(class_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void OnFailure(String err) {

            }
        });

    }

    public void OpenClass(View view) {
        Intent openclass = new Intent(getApplicationContext(),TeacherClass.class);
        startActivity(openclass);
    }

    public void openDiary(View view) {
        Intent opendiary = new Intent(getApplicationContext(),StudentSelect.class);
        opendiary.putExtra("class_name","diary");
        startActivity(opendiary);
    }

    public void openAttendence(View view) {
        Intent openattnds = new Intent(getApplicationContext(),Teacherattendence.class);
//        Intent openattnds = new Intent(getApplicationContext(),EditData.class);
        startActivity(openattnds);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TeacherDashboard.super.onBackPressed();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }

    public void openAbout(View view) {
        Intent opendiary = new Intent(getApplicationContext(), About.class);
        startActivity(opendiary);
    }

    public void openProfile(View view) {
        Intent opendiary = new Intent(getApplicationContext(), TeacherProfile.class);
        startActivity(opendiary);
    }
}
