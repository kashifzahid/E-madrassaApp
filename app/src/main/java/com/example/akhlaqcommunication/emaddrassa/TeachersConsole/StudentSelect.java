package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.akhlaqcommunication.emaddrassa.Model.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.classRecycler;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentSelect extends AppCompatActivity {
    private Spinner student;
    private ArrayList<String> studentList;
    private ArrayList<Modelclass> modelClassList;
    private SharedPreferenceEdit sharedPreferenceEdit;
    private String class_id,class_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select);
        studentList=new ArrayList<>();
        modelClassList=new ArrayList<>();
        student = findViewById(R.id.spinner_student);
        Intent intent=getIntent();
        class_name = intent.getStringExtra("class_name");
        sharedPreferenceEdit=new SharedPreferenceEdit(StudentSelect.this);

        class_id=sharedPreferenceEdit.GetClassId();
        getStudent(class_id);
    }

    private void getStudent(String id) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","TeacherAllStudents");
//            Log.e("tag", "getDashboard: "+id );
            jsonObject.put("id",id);
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
                        studentList.add(name);
                        modelClassList.add(new Modelclass(id,R.drawable.profileicon,name,roll,
                                semester_no,"Class"));

                    }
                    student.setAdapter(new ArrayAdapter<String>(StudentSelect.this, android.R.layout.simple_spinner_dropdown_item, studentList));

                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

            @Override
            public void OnFailure(String err) {
                Log.e("eheh", "OnFailure: "+err );

            }
        });
    }

    public void select_student(View view) {
       String id= modelClassList.get(student.getSelectedItemPosition()).getId();
        if(class_name.equals("diary")){
            Intent intent=new Intent(StudentSelect.this,DailyDiary2.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }else if(class_name.equals("exams")){
            Intent intent=new Intent(StudentSelect.this,TeacherExam.class);
            startActivity(intent);
        }


    }
}
