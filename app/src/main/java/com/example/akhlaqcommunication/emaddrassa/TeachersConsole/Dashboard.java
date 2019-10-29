package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Splash;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity {

    private Toolbar mtoolbar;
    LinearLayout assignment_layout,clas_layout,attendence_layout,exam_layout,result_layout;
    private Button daily_diarybtn;
    private String class_id,teacher_id;
    private SharedPreferenceEdit sharedPreferenceEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //top toolbar
        mtoolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Dashboard");
         sharedPreferenceEdit=new SharedPreferenceEdit(Dashboard.this);
        String status=sharedPreferenceEdit.GetClassStatus();
        teacher_id=sharedPreferenceEdit.GetDriverId();
        if(status.equals("login")){

            class_id=sharedPreferenceEdit.GetClassId();
        }else{
            getClassId();
        }
        getDashboard();

    }

    private void getDashboard() {

            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("screen","TeacherDasboard");
                jsonObject.put("id",class_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            VolleyRequest.PostRequest(Dashboard.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
                @Override
                public void OnSuccess(JSONObject jsonObject) {
                    try {
                        String total=jsonObject.getString("total");
                        String absent=jsonObject.getString("absent");
                        String attend=jsonObject.getString("attend");

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


        VolleyRequest.PostRequest(Dashboard.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    class_id=jsonObject.getString("ClassId");
                    sharedPreferenceEdit.AddClassId(class_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void OnFailure(String err) {

            }
        });

    }

    public void openresult(View v) {
        Intent openattnds = new Intent(getApplicationContext(),TeacherResult.class);
        startActivity(openattnds);
    }
    public void openexam(View v) {
        Intent openattnds = new Intent(getApplicationContext(),TeacherExam.class);
        startActivity(openattnds);
    }
    public void openAttendence(View v) {
        Intent openattnds = new Intent(getApplicationContext(),Teacherattendence.class);
        startActivity(openattnds);
    }
    public void openClass(View v) {
        Intent openclass = new Intent(getApplicationContext(),TeacherClass.class);
        startActivity(openclass);
    }
    public void openAssignment(View v) {
        Intent openassignment = new Intent(getApplicationContext(),TeacherAssignment.class);
        startActivity(openassignment);
    }
    public void opendailydiary(View v) {
        Intent opendiary = new Intent(getApplicationContext(),DailyDiary.class);
        startActivity(opendiary);
    }
    public void openteacherevent(View V){
        Intent openevent = new Intent(getApplicationContext(),Teacher_events.class);
        startActivity(openevent);
    }
}
