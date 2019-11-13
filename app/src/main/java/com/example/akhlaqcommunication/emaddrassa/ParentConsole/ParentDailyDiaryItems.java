package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.akhlaqcommunication.emaddrassa.Model.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.parent_attendence_recycler;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.parent_dailydiary_recycler;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParentDailyDiaryItems extends AppCompatActivity {
    private Toolbar mtoolbar;
    private SharedPreferenceEdit sharedPreferenceEdit;
    private String id;
    private ArrayList<Modelclass> modelClassList;
    private parent_dailydiary_recycler adapter;
    private RecyclerView dailydiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_daily_diary_items);
        mtoolbar = findViewById(R.id.parent_dailydiary_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Daily Diary ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferenceEdit=new SharedPreferenceEdit(ParentDailyDiaryItems.this);
        id=sharedPreferenceEdit.GetStudentId();
        dailydiary=findViewById(R.id.recycler_diary);
        modelClassList = new ArrayList<>();

        dailydiary.setHasFixedSize(true);
        dailydiary.setLayoutManager(new LinearLayoutManager(this));
     getHistoryDiary();


    }
    private void getHistoryDiary()
    {
        String url=Urls.GetDiaryParent+"?screen=GETDIARYBYID&id="+id;
        Log.e("daily diary", "getHistoryDiary: "+url );


        VolleyRequest.GetRequest(ParentDailyDiaryItems.this, url,  new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {

                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++){

                        String id=jsonArray.getJSONObject(i).getString("DailyDiaryId");
                        String teacher=jsonArray.getJSONObject(i).getString("TeacherName");
                        String Remarks=jsonArray.getJSONObject(i).getString("Remarks");
                        String dates=jsonArray.getJSONObject(i).getString("DailyDiaryCreatedOn");
                        String grade=jsonArray.getJSONObject(i).getString("DailyDiaryGradeq");
                        modelClassList.add(new Modelclass(id,dates,teacher,Remarks,grade));

                    }

                    adapter = new parent_dailydiary_recycler(ParentDailyDiaryItems.this, modelClassList);
                    dailydiary.setAdapter(adapter);

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
