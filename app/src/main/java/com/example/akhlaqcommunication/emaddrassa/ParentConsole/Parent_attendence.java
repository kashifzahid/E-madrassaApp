package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.akhlaqcommunication.emaddrassa.Model.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.classRecycler;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.parent_attendence_recycler;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parent_attendence extends AppCompatActivity {

    private Toolbar mtoolbar;
    private TextView present,absent;
    private RecyclerView attendance;
    private String id;
    private SharedPreferenceEdit sharedPreferenceEdit;
    private ArrayList<Modelclass> modelClassList;
    private parent_attendence_recycler adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_attendence);

        //top toolbar
        mtoolbar = findViewById(R.id.parent_attendence_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Attendence");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        present=findViewById(R.id.present);
        absent=findViewById(R.id.absent);
        attendance=findViewById(R.id.attendence_recycler);
        modelClassList = new ArrayList<>();

        attendance.setHasFixedSize(true);
        attendance.setLayoutManager(new LinearLayoutManager(this));
        sharedPreferenceEdit=new SharedPreferenceEdit(Parent_attendence.this);
        id=sharedPreferenceEdit.GetStudentId();
        getTotalAttendance();
        getHistoryAttendance();
    }

    private void getTotalAttendance()  {


            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("screen","ParentDashboard");
                Log.e("tag", "getDashboard: "+id );
                jsonObject.put("id",id);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            VolleyRequest.PostRequest(Parent_attendence.this, Urls.TeacherDashboard, jsonObject, new VolleyPostCallBack() {
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



    private void getHistoryAttendance() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","ParentAttendanceHistory");
            Log.e("tag", "getDashboard: "+id );
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        VolleyRequest.PostRequest(Parent_attendence.this, Urls.TeacherDashboardDetails, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {

                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++){
                        String dates=jsonArray.getJSONObject(i).getString("date");
                        String statuss=jsonArray.getJSONObject(i).getString("status");
                        modelClassList.add(new Modelclass(dates,statuss));

                    }

                    adapter = new parent_attendence_recycler(Parent_attendence.this, modelClassList);
                    attendance.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void OnFailure(String err) {

            }
        });

    }
}
