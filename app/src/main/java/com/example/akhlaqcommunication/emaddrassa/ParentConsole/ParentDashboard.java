package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.DailyDiary;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.Dashboard;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.TeacherResult;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ParentDashboard extends AppCompatActivity {
    private String student_id,parent_id;
    private TextView present,absent;
    private SharedPreferenceEdit sharedPreferenceEdit;

    private Toolbar mtoolbar;
    Button parentdailydiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_dashboard);

        mtoolbar = findViewById(R.id.parent_dashboard_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Dashboard");
        sharedPreferenceEdit=new SharedPreferenceEdit(ParentDashboard.this);
        present=findViewById(R.id.no_of_present_students);
        absent=findViewById(R.id.no_of_absent_students);
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


        VolleyRequest.PostRequest(ParentDashboard.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {

                    String absents=jsonObject.getString("absent_count");
                    String attends=jsonObject.getString("present_count");


                    absent.setText(absents);
                    present.setText(attends);

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


        VolleyRequest.PostRequest(ParentDashboard.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
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
    public void openevents(View v) {
        Intent openattnds = new Intent(getApplicationContext(), ParentEvent.class);
        startActivity(openattnds);
    }

    public void openfee(View v) {

        Intent openattnds = new Intent(getApplicationContext(),ParentFee.class);
        startActivity(openattnds);

    }

    public void openresult(View v) {
        Intent openattnds = new Intent(getApplicationContext(),ParentResult.class);
        startActivity(openattnds);

    }

    public void openPerformnce(View v) {
        Intent openattnds = new Intent(getApplicationContext(),PerformenceParent.class);
        startActivity(openattnds);

    }

    public void opendailydiary(View v) {
        Intent opendiary = new Intent(getApplicationContext(), ParentDailyDiary.class);
        startActivity(opendiary);
    }

    public void openparentattendence(View v) {
        Intent opendiary = new Intent(getApplicationContext(), Parent_attendence.class);
        startActivity(opendiary);
    }

}
