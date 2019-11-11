package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Model.ModelSurah;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DailyDiary extends AppCompatActivity  {

    private static final String TAG = "DailyDiary";
    private Toolbar mtoolbar;
    private Spinner student, new_surah, sabq_surah, sabqi_surah, manzil_surah, new_from, sabq_from, sabqi_from, manzil_from, new_to, sabq_to, sabqi_to, manzil_to, sabq_grade, sabqi_grade, manzil_grade, academics_grade, namaz_grade, overall_grade;
    private ArrayList<String> students;
    private ArrayList<String> surah;
    private ArrayList<ModelSurah> surahs;
    private EditText teacher_remarks;
    private RadioGroup radioGroupfajar, radioGroupzuhar, radioGroupasar, radioGroupmaghrib, radioGroupisha;
    private String id;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_diary);

        //top toolbar
        mtoolbar = findViewById(R.id.teacher_dailydiary_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Daily Diary");
        context = this;
        surah = new ArrayList<>();
        surahs = new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        // RadioGroup
        radioGroupfajar = findViewById(R.id.radioGroupfajar);
        radioGroupzuhar = findViewById(R.id.radioGroupzuhar);
        radioGroupasar = findViewById(R.id.radioGroupasar);
        radioGroupmaghrib = findViewById(R.id.radioGroupmaghrib);
        radioGroupisha = findViewById(R.id.radioGroupisha);

        // EditText
        teacher_remarks = findViewById(R.id.remarks_teacher);

        //Spinner

        //surah spinner
//        new_surah = findViewById(R.id.spinner_new_surah);
//        sabq_surah = findViewById(R.id.spinner_sabq_surah);
//        sabqi_surah = findViewById(R.id.spinner_sabqi_surah);
//        manzil_surah = findViewById(R.id.spinner_manzil_surah);
//        //ayat from spinner
//        new_from = findViewById(R.id.spinner_new_from);
//        sabq_from = findViewById(R.id.spinner_sabq_from);
//        sabqi_from = findViewById(R.id.spinner_sabqi_from);
//        manzil_from = findViewById(R.id.spinner_manzil_from);
//
//        // ayat to spinner
//        new_to = findViewById(R.id.spinner_new_to);
//        sabq_to = findViewById(R.id.spinner_sabq_to);
//        sabqi_to = findViewById(R.id.spinner_sabqi_to);
//        manzil_to = findViewById(R.id.spinner_manzil_to);

        // grade spinner
        sabq_grade = findViewById(R.id.spinner_sabq_grade);
        sabqi_grade = findViewById(R.id.spinner_sabqi_grade);
        manzil_grade = findViewById(R.id.spinner_manzil_grade);

        // Overall grade
        academics_grade = findViewById(R.id.spinner_academics_grade);
        namaz_grade = findViewById(R.id.spinner_namaz_grade);
        overall_grade = findViewById(R.id.spinner_overall_grade);

        ArrayList<String> grade = new ArrayList<String>();
        grade.add("A");
        grade.add("B");
        grade.add("C");
        grade.add("D");
        sabq_grade.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, grade));
        sabqi_grade.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, grade));
        manzil_grade.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, grade));
        academics_grade.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, grade));
        namaz_grade.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, grade));
        overall_grade.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, grade));


//        getSurah();
//
//        new_surah.setOnItemSelectedListener(this);
//        sabq_surah.setOnItemSelectedListener(this);
//        sabqi_surah.setOnItemSelectedListener(this);
//        manzil_surah.setOnItemSelectedListener(this);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//    }
//
//    public void SubmitDiary(View view) throws JSONException {
//
//        // Surah
//        String neww = surahs.get(new_surah.getSelectedItemPosition()).getSurah_number();
//        String sabq = surahs.get(sabq_surah.getSelectedItemPosition()).getSurah_number();
//        String sabqi = surahs.get(sabqi_surah.getSelectedItemPosition()).getSurah_number();
//        String manzil = surahs.get(manzil_surah.getSelectedItemPosition()).getSurah_number();
//
//        // from
//        String from_new =(String)  new_from.getSelectedItem();
//        String from_sabq = (String)  sabq_from.getSelectedItem();
//        String from_sabqi = (String)  sabqi_from.getSelectedItem();
//        String from_manzil = (String)  manzil_from.getSelectedItem();
//
//        // to
//        String to_new    = (String) new_to.getSelectedItem();
//        String to_sabq   = (String)  sabq_to.getSelectedItem();
//        String to_sabqi  = (String) sabqi_to.getSelectedItem();
//        String to_manzil = (String) manzil_to.getSelectedItem();
//
//        // grade
//        String grade_sabq = (String) sabq_grade.getSelectedItem();
//        String grade_sabqi = (String) sabqi_grade.getSelectedItem();
//        String grade_manzil = (String) manzil_grade.getSelectedItem();
//
//        // overall grade
//        String grade_academic = (String) academics_grade.getSelectedItem();
//        String grade_namaz = (String) namaz_grade.getSelectedItem();
//        String grade_overall = (String) overall_grade.getSelectedItem();
//
//        Log.d(TAG, "Sabaq Grade " + grade_sabq);
//
//
//
//        int selectedIdfajar = radioGroupfajar.getCheckedRadioButtonId();
//        int selectedIdzuhar = radioGroupzuhar.getCheckedRadioButtonId();
//        int selectedIdasar = radioGroupasar.getCheckedRadioButtonId();
//        int selectedIdmaghrib = radioGroupmaghrib.getCheckedRadioButtonId();
//        int selectedIdisha = radioGroupisha.getCheckedRadioButtonId();
//
//        // find the radiobutton by returned id
//        RadioButton radioButtonfajar = (RadioButton) findViewById(selectedIdfajar);
//        RadioButton radioButtonzuhar = (RadioButton) findViewById(selectedIdzuhar);
//        RadioButton radioButtonmaghrib = (RadioButton) findViewById(selectedIdmaghrib);
//        RadioButton radioButtonasar = (RadioButton) findViewById(selectedIdasar);
//        RadioButton radioButtonisha = (RadioButton) findViewById(selectedIdisha);
//        String fajr=radioButtonfajar.getText().toString();
//        String zuhr=radioButtonzuhar.getText().toString();
//        String asr=radioButtonasar.getText().toString();
//        String mag=radioButtonmaghrib.getText().toString();
//        String isha=radioButtonisha.getText().toString();
//
//        //DAILY DIARY OBJECT
//        JSONObject jsonObject=new JSONObject();
//        //NEW SABQ OBJECT
//        JSONObject nsobject=new JSONObject();
//        nsobject.put("surah",neww);
//        nsobject.put("from",from_new);
//        nsobject.put("to",to_new);
//        //SABQ OBJECT
//
//        JSONObject sobject=new JSONObject();
//        sobject.put("surah",sabq);
//        sobject.put("from",from_sabq);
//        sobject.put("to",to_sabq);
//        sobject.put("grade",grade_sabq);
//        //SABQI OBJECT
//
//        JSONObject siobject=new JSONObject();
//        siobject.put("surah",sabqi);
//        siobject.put("from",from_sabqi);
//        siobject.put("to",to_sabqi);
//        siobject.put("grade",grade_sabqi);
//        //MANZIL OBJECT
//        JSONObject mobject=new JSONObject();
//        mobject.put("surah",manzil);
//        mobject.put("from",from_manzil);
//        mobject.put("to",to_manzil);
//        mobject.put("grade",grade_manzil);
//        JSONObject naobject=new JSONObject();
//        naobject.put("fajr",fajr);
//        naobject.put("zuhr",zuhr);
//        naobject.put("asar",asr);
//        naobject.put("maghrib",mag);
//        naobject.put("isha",isha);
//        naobject.put("grade",grade_namaz);
//
//        String remarks=teacher_remarks.getText().toString();
//         //put json objects
//        jsonObject.put("new_sabq_object",nsobject);
//        jsonObject.put("sabq_object",sobject);
//        jsonObject.put("sabqi_object",siobject);
//        jsonObject.put("manzil_object",mobject);
//        jsonObject.put("namaz",naobject);
//        jsonObject.put("remarks",remarks);
//        jsonObject.put("overall",grade_overall);
//
//
//        Log.e(TAG, "SubmitDiary: "+jsonObject );
//        String url = Urls.PostDiary ;
//        //Post Request
//        VolleyRequest.PostRequest(context, url, jsonObject, new VolleyPostCallBack() {
//            @Override
//            public void OnSuccess(JSONObject jsonObject) {
//                Intent intent=new Intent(DailyDiary.this,Dashboard.class);
//                startActivity(intent);
//
//            }
//
//            @Override
//            public void OnFailure(String err) {
//
//            }
//        });
//
//
//
//    }
//
//    private void getSurah() {
//        String url = Urls.GetSurah + "?screen=GETSURAH";
//        VolleyRequest.GetRequest(context, url, new VolleyPostCallBack() {
//            @Override
//            public void OnSuccess(JSONObject jsonObject) {
//                try {
//                    JSONArray jsonArray = jsonObject.getJSONArray("result");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject js = (JSONObject) jsonArray.get(i);
//                        String surah_id = js.getString("surah_id");
//                        String surah_no = js.getString("Surah_no");
//                        Log.e("tag", "OnSuccess: " + surah_no);
//                        String ayat = js.getString("Total_ayat");
//                        int ayats = Integer.parseInt(ayat);
//                        String name = js.getString("Surah_name");
//                        surahs.add(new ModelSurah(name, surah_no, surah_id, ayats));
//
//                        surah.add(name);
//
//                    }
//
//                    new_surah.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, surah));
//                    sabq_surah.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, surah));
//                    sabqi_surah.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, surah));
//                    manzil_surah.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, surah));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void OnFailure(String err) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
//
//        ArrayList<String> ayats = new ArrayList<>();
//        switch (parent.getId()) {
//            case R.id.spinner_new_surah:
//                //surahs.get(i).getSurah_ayat();
//                //Do something
//                ayats.clear();
//                for (int j = 0; j < surahs.get(i).getSurah_ayat(); j++) {
//                    ayats.add((j + 1) + "");
//                }
//                new_from.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//                new_to.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//
//
//                Toast.makeText(this, "Alarm Selected: " + parent.getSelectedItem().toString() + " " + surahs.get(i).getSurah_ayat() + "", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.spinner_sabq_surah:
//                //Do another thing
//                ayats.clear();
//                for (int j = 0; j < surahs.get(i).getSurah_ayat(); j++) {
//                    ayats.add((j + 1) + "");
//                }
//                sabq_from.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//                sabq_to.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//
//                Toast.makeText(this, "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.spinner_sabqi_surah:
//                //Do another thing
//                ayats.clear();
//                for (int j = 0; j < surahs.get(i).getSurah_ayat(); j++) {
//                    ayats.add((j + 1) + "");
//                }
//                sabqi_from.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//                sabqi_to.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//
//                Toast.makeText(this, "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.spinner_manzil_surah:
//                //Do another thing
//                ayats.clear();
//                for (int j = 0; j < surahs.get(i).getSurah_ayat(); j++) {
//                    ayats.add((j + 1) + "");
//                }
//                manzil_from.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//                manzil_to.setAdapter(new ArrayAdapter<String>(DailyDiary.this, android.R.layout.simple_spinner_dropdown_item, ayats));
//
//                Toast.makeText(this, "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }


    }
}