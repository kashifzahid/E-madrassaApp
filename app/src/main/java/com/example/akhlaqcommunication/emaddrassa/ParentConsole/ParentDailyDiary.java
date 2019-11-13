package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParentDailyDiary extends AppCompatActivity {

    private static final String TAG ="ParentDailyDiary" ;
    private Toolbar mtoolbar;
    private String sabq_para,sabq_surah_from,sabq_ayat_from,sabq_surah_to,sabq_ayat_to,sabqi_para,sabqi_surah_to,sabqi_ayat_to,manzil_para_start,manzil_para_end;
    private String sabq_grade,sabqi_grade,manzil_grade,namaz_grade,overall_grade,remarks,date,teacher;
    private String fajr,duhr,asr,maghrib,isha;
    private SharedPreferenceEdit sharedPreferenceEdit;
    private String id;
    private TextView sabq_text,sabqi_text,manzil_text,namaz_text,namaz_text_grade,overall_text_grade,remarks_text,teacher_text,date_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_daily_diary);

        //top toolbar
        mtoolbar = findViewById(R.id.parent_dailydiary_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Daily Diary Detail");
        sabq_text=findViewById(R.id.sabq_text);
        sabqi_text=findViewById(R.id.sabqi_text);
        manzil_text=findViewById(R.id.manzil_text);
        namaz_text=findViewById(R.id.namaz_text);
        namaz_text_grade=findViewById(R.id.namaz_grade);
        overall_text_grade=findViewById(R.id.overall_grade);
        remarks_text=findViewById(R.id.remarks_text);
        teacher_text=findViewById(R.id.teacher_text);
        date_text=findViewById(R.id.date_text);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        id = intent.getStringExtra("diary_id");
        getDailyDiary(id);


    }

    private void getDailyDiary(String id) {
        String url = Urls.GetDiaryParent + "?screen=GETDIARYDETAILBYID&id="+id;
        Log.e(TAG, "getDiaryDetail: "+url );

        VolleyRequest.GetRequest(ParentDailyDiary.this, url, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject data=jsonArray.getJSONObject(0);
                    Log.e(TAG, "OnSuccess datas is + dudhb : "+data );
                    sabq_para=data.getString("sabaq_para");
                    sabq_surah_from=data.getString("sabaq_surah_from");
                    sabq_surah_to=data.getString("sabaq_surah_to");
                    sabq_ayat_from=data.getString("sabaq_ayat_from");
                    sabq_ayat_to=data.getString("sabaq_ayat_to");
                    sabq_grade=data.getString("sabaq_grade");
                    sabqi_para=data.getString("sabqi_para");
                    sabqi_surah_to=data.getString("sabqi_surah");
                    sabqi_ayat_to=data.getString("sabqi_ayat");
                    sabqi_grade=data.getString("sabqi_grade");
                    manzil_para_start=data.getString("manzil_para_from");
                    manzil_para_end=data.getString("manzil_para_to");
                    manzil_grade=data.getString("manzil_grade");
                    fajr=data.getString("Fajar");
                    duhr=data.getString("Duhr");
                    asr=data.getString("Asar");
                    maghrib=data.getString("Maghrib");
                    isha=data.getString("Isha");
                    namaz_grade=data.getString("namaz_grade");
                    date=data.getString("DailyDiaryCreatedOn");
                    teacher=data.getString("TeacherName");
                    overall_grade=data.getString("DailyDiaryGradeq");
                    remarks=data.getString("Remarks");

                    setDailyDiary();





                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }

            @Override
            public void OnFailure(String err) {
                Log.e(TAG, "OnFailure: "+err );

            }
        });

    }

    private void setDailyDiary() {
        sabq_text.setText("Para : "+ sabq_para+  " Surah  :"+sabq_surah_from+" , Ayat :"+sabq_ayat_from+" to Surah :"+sabq_surah_to+" ,Ayat :"+sabq_ayat_to+" ,Grade :"+sabq_grade);
        sabqi_text.setText("Para : "+ sabqi_para+  " Surah  :"+sabqi_surah_to+" , Ayat :"+sabqi_ayat_to+" ,Grade :"+sabqi_grade);
        manzil_text.setText("Para Start: "+manzil_para_start +" Para end: "+manzil_para_end+" ,Grade :"+manzil_grade);
        teacher_text.setText("Teacher Name : "+teacher);
        date_text.setText("Created On : "+date);
        remarks_text.setText(remarks);
        namaz_text.setText("Fajar : "+fajr+" Duhr : "+duhr+" Asar : "+asr+" Maghrib : "+maghrib+" Isha : "+isha);
        namaz_text_grade.setText(namaz_grade);
        overall_text_grade.setText(overall_grade);



    }

}
