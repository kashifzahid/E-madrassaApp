package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.classRecycler;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeacherClass extends AppCompatActivity {

    private Toolbar mtoolbar;
    private RecyclerView recyclerView;
    private classRecycler adaptor;
    List<Modelclass> modelClassList;
    private SharedPreferenceEdit sharedPreferenceEdit;
    private String class_id;
   private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class);

        mtoolbar = findViewById(R.id.teacher_class_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Class");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context=this;
        sharedPreferenceEdit=new SharedPreferenceEdit(TeacherClass.this);

        class_id=sharedPreferenceEdit.GetClassId();
        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.student_preview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","TeacherAllStudents");
            Log.e("tag", "getDashboard: "+class_id );
            jsonObject.put("id",class_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyRequest.PostRequest(this, Urls.TeacherStudents, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject js) {
                try {
                    JSONArray jsonArray=new JSONArray();
                    Log.e("yes run", "OnSuccess: "+jsonArray.length());
                    jsonArray=js.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = (JSONObject) jsonArray.get(i);

                        String id = jsonObject.getString("StudentId");
                        String name = jsonObject.getString("StudentName");
                        String roll = jsonObject.getString("StudentRollNo");
                        String pic = jsonObject.getString("StudentPic");
                        String semester_no=jsonObject.getString("semester_no");
                        modelClassList.add(new Modelclass(R.drawable.profileicon,name,roll,
                                semester_no,"Class"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                adaptor = new classRecycler(context, modelClassList);
                recyclerView.setAdapter(adaptor);
            }

            @Override
            public void OnFailure(String err) {
                Log.e("eheh", "OnFailure: "+err );

            }
        });


    }
}
