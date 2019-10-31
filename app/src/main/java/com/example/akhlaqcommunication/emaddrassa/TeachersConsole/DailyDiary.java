package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DailyDiary extends AppCompatActivity {

    private Toolbar mtoolbar;
    private Spinner student,new_surah,sabq_surah,sabqi_surah,manzil_surah,new_from,sabq_from,sabqi_from,manzil_from,new_to,sabq_to,sabqi_to,manzil_to,sabq_grade,sabqi_grade,manzil_grade;
    private ArrayList<String> students;
    private ArrayList<String> surah;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_diary);

        //top toolbar
        mtoolbar = findViewById(R.id.teacher_dailydiary_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Daily Diary");
        context=this;
        surah=new ArrayList<>();
        //Spinner
        student=findViewById(R.id.spinner_student);
        //surah spinner
        new_surah=findViewById(R.id.spinner_new_surah);
        sabq_surah=findViewById(R.id.spinner_sabq_surah);
        sabqi_surah=findViewById(R.id.spinner_sabqi_surah);
        manzil_surah=findViewById(R.id.spinner_manzil_ayat);
        //ayat from spinner
        new_from=findViewById(R.id.spinner_new_from);
        sabq_from=findViewById(R.id.spinner_sabq_from);
        sabqi_from=findViewById(R.id.spinner_sabqi_from);
        manzil_from=findViewById(R.id.spinner_manzil_from);

        // ayat to spinner
        new_to=findViewById(R.id.spinner_new_to);
        sabq_to=findViewById(R.id.spinner_sabq_to);
        sabqi_to=findViewById(R.id.spinner_sabqi_to);
        manzil_to=findViewById(R.id.spinner_manzil_to);

        // grade spinner
        sabq_grade=findViewById(R.id.spinner_sabq_grade);
        sabqi_grade=findViewById(R.id.spinner_sabqi_grade);
        manzil_grade=findViewById(R.id.spinner_manzil_grade);

        getSurah();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        }





    private void getSurah() {
        String url=Urls.GetSurah+"?screen=GETSURAH";
        VolleyRequest.GetRequest(context, url, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject js= (JSONObject) jsonArray.get(i);
                      String surah_id=js.getString("surah_id");
                       String surah_no= js.getString("Surah_no");
                        Log.e("tag", "OnSuccess: "+surah_no );
                       String ayat= js.getString("Total_ayat");
                     String name= js.getString("Surah_name");
                        surah.add(name);

                    }

                    new_surah.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, surah));
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
