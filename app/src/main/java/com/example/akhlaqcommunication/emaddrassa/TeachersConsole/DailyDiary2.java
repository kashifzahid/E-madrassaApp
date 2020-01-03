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
import android.widget.TextView;
import android.widget.Toast;


import com.example.akhlaqcommunication.emaddrassa.Model.ModelSurah;
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DailyDiary2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "DailyDiary";
    private Toolbar mtoolbar;
    private Context context;
    private ArrayList<String> para;
    private String sabq_para_id,sabqi_para_id,manzil_para_start_id,manzil_para_end_id,manzil_para_start,manzil_para_end,sabq_para,sabqi_para,sabq_surah_no_from,sabq_surah_no_to,sabq_surah_from,sabq_surah_to,sabqi_surah_no_to,sabqi_surah_to,sabq_ayat_from,sabq_ayat_to,sabqi_ayat_to;
    private TextView sabq_text,sabqi_text,manzil_text;
    private ArrayList<String> surah;
    private ArrayList<ModelSurah> surahs;
    private ArrayList<ModelSurah> paras;
    private EditText teacher_remarks;
    private RadioGroup radioGroupfajar, radioGroupzuhar, radioGroupasar, radioGroupmaghrib, radioGroupisha;
    private String id,teacher_id;
    private Spinner new_para,new_surah_from,new_surah_to,new_ayat_from,new_ayat_to,sabq_grade,sabqi_grade,manzil_grade,namaz_grade,overall_grade;
    private SharedPreferenceEdit sharedPreferenceEdit;
    private String status,url;
    private JSONObject jsonObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_diary2);
        //top toolbar
        mtoolbar = findViewById(R.id.teacher_dailydiary_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Daily Diary 2" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        para = new ArrayList<>();
        paras=new ArrayList<>();
        surahs = new ArrayList<>();
        surah=new ArrayList<>();
        Intent intent=getIntent();
        sharedPreferenceEdit=new SharedPreferenceEdit(DailyDiary2.this);
        id = intent.getStringExtra("id");
        teacher_id=sharedPreferenceEdit.GetDriverId();

        // Text view
        sabq_text=findViewById(R.id.text_sabq);
        sabqi_text=findViewById(R.id.text_sabqi);
        manzil_text=findViewById(R.id.text_manzil);

        // RadioGroup
        radioGroupfajar = findViewById(R.id.radioGroupfajar);
        radioGroupzuhar = findViewById(R.id.radioGroupzuhar);
        radioGroupasar = findViewById(R.id.radioGroupasar);
        radioGroupmaghrib = findViewById(R.id.radioGroupmaghrib);
        radioGroupisha = findViewById(R.id.radioGroupisha);

        // EditText
        teacher_remarks = findViewById(R.id.remarks_teacher);

        // Spinner new sabaq
        new_para=findViewById(R.id.spinner_new_para);
        new_surah_from=findViewById(R.id.spinner_new_surah_from);
        new_surah_to=findViewById(R.id.spinner_new_surah_to);
        new_ayat_from=findViewById(R.id.spinner_new_ayat_from);
        new_ayat_to=findViewById(R.id.spinner_new_ayat_to);

        // grade spinner
        sabq_grade = findViewById(R.id.spinner_sabq_grade);
        sabqi_grade = findViewById(R.id.spinner_sabqi_grade);
        manzil_grade = findViewById(R.id.spinner_manzil_grade);
        namaz_grade = findViewById(R.id.spinner_namaz_grade);

        // Overall grade


        overall_grade = findViewById(R.id.spinner_overall_grade);
       status="yes";

        ArrayList<String> grade = new ArrayList<String>();
        grade.add("A");
        grade.add("B");
        grade.add("C");
        grade.add("D");
        sabq_grade.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, grade));
        sabqi_grade.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, grade));
        manzil_grade.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, grade));
        namaz_grade.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, grade));
        overall_grade.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, grade));
        getsabqstatus(id);
//        if (status.equals("yes")) {
//
//
//
//        }
        getDiaryDetail(id);
        getPara();
new_para.setOnItemSelectedListener(this);
new_surah_from.setOnItemSelectedListener(this);
new_surah_to.setOnItemSelectedListener(this);

//       getSurah();

    }

    private void getsabqstatus(String id) {
        String url = Urls.GetSabqStatus + "?screen=GETSABQSTATUS&id="+id;
        Log.e(TAG, "getsabqstatus: "+url );

        VolleyRequest.GetRequest(context, url, new VolleyPostCallBack() {

            @Override
            public void OnSuccess(JSONObject jsonObject) {
                Log.e(TAG, "OnSuccess called is : "+jsonObject );
                try {

                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    int id=jsonArray.getJSONObject(0).getInt("id");
                    Log.e(TAG, "OnSuccess:---------------------- "+id );
                    if(id==0){
                        status="no";
                    }else{
                        status="yes";

                    }


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

    private void getPara() {
        String url = Urls.GetSurah + "?screen=GETPARA";
        VolleyRequest.GetRequest(context, url, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject js = (JSONObject) jsonArray.get(i);

                        String id = js.getString("Siparah_id");

                        String no = js.getString("siparha_no");

                        String name = js.getString("siparah_name");

                        paras.add(new ModelSurah(id,name,no));

                        para.add(name);

                    }

                    new_para.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, para));

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
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        ArrayList<String> ayats = new ArrayList<>();
        switch (parent.getId()) {
            case R.id.spinner_new_para:
                //surahs.get(i).getSurah_ayat();
                //Do something
                surahs.clear();
                new_surah_from.setAdapter(null);
                new_surah_to.setAdapter(null);
                String neww = paras.get(new_para.getSelectedItemPosition()).getPara_id();
               getParaSurah(neww);


//                Toast.makeText(this, "Alarm Selected: " + parent.getSelectedItem().toString() + " " + surahs.get(i).getSurah_ayat() + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner_new_surah_from:
                //Do another thing
                ayats.clear();
               Log.e(TAG, "onItemSelected----------------------------: "+surahs.size() );
                for (int j = surahs.get(i).getSurah_ayat_start(); j < surahs.get(i).getSurah_ayat_end(); j++) {
                    ayats.add((j ) + "");
                }
                new_ayat_from.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, ayats));

                Toast.makeText(this, "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner_new_surah_to:
                //Do another thing
                ayats.clear();
               Log.e(TAG, "onItemSelected--------------------------------- end : "+surahs.size() );
                for (int j = surahs.get(i).getSurah_ayat_start(); j < surahs.get(i).getSurah_ayat_end(); j++) {

                    ayats.add((j ) + "");
                }
                new_ayat_to.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, ayats));

                Toast.makeText(this, "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getParaSurah(String id) {
        String url = Urls.GetSurah + "?screen=GETSURAHBYPARA&id="+id;
        Log.e(TAG, "getParaSurah: "+url );
        surahs.clear();
        surah.clear();
        VolleyRequest.GetRequest(context, url, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject js = (JSONObject) jsonArray.get(i);

                        String surah_no = js.getString("Surah_no");
                        Log.e("tag", "OnSuccess: " + surah_no);
                        String ayat_start = js.getString("VerseFrom");
                        int ayats_f = Integer.parseInt(ayat_start);
                        String ayat_end = js.getString("VerseTo");
                        int ayats_t = Integer.parseInt(ayat_end);
                        String name = js.getString("Surah_name");
                        surahs.add(new ModelSurah(name, surah_no,  ayats_f,ayats_t));

                        surah.add(name);

                    }

                    new_surah_from.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, surah));
                    new_surah_to.setAdapter(new ArrayAdapter<String>(DailyDiary2.this, android.R.layout.simple_spinner_dropdown_item, surah));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void OnFailure(String err) {

            }
        });
    }
    private void getDiaryDetail(String studentId){
        String url = Urls.GetDiary + "?&id="+studentId;
        Log.e(TAG, "getDiaryDetail: "+url );

        VolleyRequest.GetRequest(context, url, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    Log.e(TAG, "OnSuccess json array is ---------- : "+jsonArray.length() );
                    JSONObject obj_sabq=jsonArray.getJSONObject(0).getJSONArray("resuls").getJSONObject(0);
                    sabq_para_id=obj_sabq.getString("Siparah_id");
                    sabq_para=obj_sabq.getString("siparah_name");
                    sabq_surah_no_from=obj_sabq.getString("Surah_no");
                    sabq_surah_from=obj_sabq.getString("surah_from");
                    sabq_surah_no_to=obj_sabq.getString("Surah_noTo");
                    sabq_surah_to=obj_sabq.getString("surah_to");
                    sabq_ayat_from=obj_sabq.getString("VerseFrom");
                    sabq_ayat_to=obj_sabq.getString("VerseTo");
                    JSONObject obj_sabqi=jsonArray.getJSONObject(1).getJSONArray("resuls").getJSONObject(0);

                    sabqi_para_id=obj_sabqi.getString("Siparah_id");
                    sabqi_para=obj_sabqi.getString("siparah_name");
                    sabqi_surah_no_to=obj_sabqi.getString("Surah_no");
                    sabqi_surah_to=obj_sabqi.getString("surah_from");
                    sabqi_ayat_to=obj_sabqi.getString("VerseFrom");
                    JSONObject obj_manzil=jsonArray.getJSONObject(2).getJSONArray("resuls").getJSONObject(0);
                    manzil_para_start_id=obj_manzil.getString("siparah_start");
                    manzil_para_end_id=obj_manzil.getString("siparah_last");
                    manzil_para_start=obj_manzil.getString("siparah_start_name");
                    manzil_para_end=obj_manzil.getString("siparah_end_name");

                   String sabq= "Para : "+ sabq_para+  " Surah  :"+sabq_surah_from+" , Ayat :"+sabq_ayat_from+" to Surah :"+sabq_surah_to+" ,Ayat :"+sabq_ayat_to;
                   String sabqi= "Para : "+ sabqi_para+  " Surah  :"+sabqi_surah_to+" , Ayat :"+sabqi_ayat_to;
                   String manzil="Para Start: "+manzil_para_start +" Para end: "+manzil_para_end;

                   sabq_text.setText(sabq);
                   sabqi_text.setText(sabqi);
                   manzil_text.setText(manzil);















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

    public void SubmitDiary(View view) throws JSONException {
        if(status.equals("yes")){



        //Para
        String npara=paras.get(new_para.getSelectedItemPosition()).getPara_id();
        String spara=sabq_para_id;
        String sipara=sabqi_para_id;
        String mspara=manzil_para_start_id;
        String mepara=manzil_para_end_id;


        // Surah
        String nsurah_from=surahs.get(new_surah_from.getSelectedItemPosition()).getSurah_number();
        String nsurah_to=surahs.get(new_surah_to.getSelectedItemPosition()).getSurah_number();
        String ssurah_from=sabq_surah_no_from;
        String ssurah_to=sabq_surah_no_to;
        String sisurah=sabqi_surah_no_to;


        // Ayat
        String nayat_from= (String) new_ayat_from.getSelectedItem();
        String nayat_to= (String) new_ayat_to.getSelectedItem();

        String sayat_from =   sabq_ayat_from;
        String sayat_to =   sabq_ayat_to;
        String siayat =   sabqi_ayat_to;



        // grade
        String grade_sabq = (String) sabq_grade.getSelectedItem();
        String grade_sabqi = (String) sabqi_grade.getSelectedItem();
        String grade_manzil = (String) manzil_grade.getSelectedItem();

        // overall grade

        String grade_namaz = (String) namaz_grade.getSelectedItem();
        String grade_overall = (String) overall_grade.getSelectedItem();

        Log.d(TAG, "Sabaq Grade " + grade_sabq);



        int selectedIdfajar = radioGroupfajar.getCheckedRadioButtonId();
        int selectedIdzuhar = radioGroupzuhar.getCheckedRadioButtonId();
        int selectedIdasar = radioGroupasar.getCheckedRadioButtonId();
        int selectedIdmaghrib = radioGroupmaghrib.getCheckedRadioButtonId();
        int selectedIdisha = radioGroupisha.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButtonfajar = (RadioButton) findViewById(selectedIdfajar);
        RadioButton radioButtonzuhar = (RadioButton) findViewById(selectedIdzuhar);
        RadioButton radioButtonmaghrib = (RadioButton) findViewById(selectedIdmaghrib);
        RadioButton radioButtonasar = (RadioButton) findViewById(selectedIdasar);
        RadioButton radioButtonisha = (RadioButton) findViewById(selectedIdisha);
        String fajr=radioButtonfajar.getText().toString();
        String zuhr=radioButtonzuhar.getText().toString();
        String asr=radioButtonasar.getText().toString();
        String mag=radioButtonmaghrib.getText().toString();
        String isha=radioButtonisha.getText().toString();

        //DAILY DIARY OBJECT
         jsonObject=new JSONObject();
        //NEW SABQ OBJECT
        JSONObject nsobject=new JSONObject();

        nsobject.put("para",npara);
        nsobject.put("surah_from",nsurah_from);
        nsobject.put("surah_to",nsurah_to);
        nsobject.put("ayat_from",nayat_from);
        nsobject.put("ayat_to",nayat_to);
        nsobject.put("student",id);

        //SABQ OBJECT

        JSONObject sobject=new JSONObject();
        sobject.put("para",spara);
        sobject.put("surah_from",ssurah_from);
        sobject.put("surah_to",ssurah_to);
        sobject.put("ayat_from",sayat_from);
        sobject.put("ayat_to",sayat_to);
        sobject.put("grade",grade_sabq);
        //SABQI OBJECT

        JSONObject siobject=new JSONObject();
        siobject.put("para",sipara);
        siobject.put("surah",sisurah);
        siobject.put("ayat",siayat);

        siobject.put("grade",grade_sabqi);
        //MANZIL OBJECT
        JSONObject mobject=new JSONObject();
        mobject.put("para_from",mspara);
        mobject.put("para_to",mepara);
        mobject.put("grade",grade_manzil);
        JSONObject naobject=new JSONObject();
        naobject.put("fajr",fajr);
        naobject.put("zuhr",zuhr);
        naobject.put("asar",asr);
        naobject.put("maghrib",mag);
        naobject.put("isha",isha);
        naobject.put("grade",grade_namaz);

        String remarks=teacher_remarks.getText().toString();
         //put json objects
        jsonObject.put("new_sabq_object",nsobject);
        jsonObject.put("sabq_object",sobject);
        jsonObject.put("sabqi_object",siobject);
        jsonObject.put("manzil_object",mobject);
        jsonObject.put("namaz",naobject);
        jsonObject.put("remarks",remarks);
        jsonObject.put("overall",grade_overall);
        jsonObject.put("student",id);
        jsonObject.put("teacher",teacher_id);


        Log.e(TAG, "SubmitDiary: "+jsonObject );
          url = Urls.PostDiary ;
        }else{
            //new sabq
            url=Urls.PostNewSabq;
            String npara=paras.get(new_para.getSelectedItemPosition()).getPara_id();
            String nsurah_from=surahs.get(new_surah_from.getSelectedItemPosition()).getSurah_number();
            String nsurah_to=surahs.get(new_surah_to.getSelectedItemPosition()).getSurah_number();
            String nayat_from= (String) new_ayat_from.getSelectedItem();
            String nayat_to= (String) new_ayat_to.getSelectedItem();

            //DAILY DIARY OBJECT
            jsonObject=new JSONObject();
            //NEW SABQ OBJECT
            JSONObject nsobject=new JSONObject();

            nsobject.put("para",npara);
            nsobject.put("surah_from",nsurah_from);
            nsobject.put("surah_to",nsurah_to);
            nsobject.put("ayat_from",nayat_from);
            nsobject.put("ayat_to",nayat_to);
            nsobject.put("student",id);
            jsonObject.put("new_sabq_object",nsobject);

        }
        //Post Request
        VolleyRequest.PostRequest(context, url, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                Intent intent=new Intent(DailyDiary2.this,Dashboard.class);
                startActivity(intent);

            }

            @Override
            public void OnFailure(String err) {

            }
        });




    }

}
