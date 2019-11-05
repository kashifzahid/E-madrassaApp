package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.example.akhlaqcommunication.emaddrassa.Interface.OnOptionSelected;
import com.example.akhlaqcommunication.emaddrassa.Model.AttendenceModel;
import com.example.akhlaqcommunication.emaddrassa.Model.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.attendence_modelclass;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.attendence_recycler;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.classRecycler;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Teacherattendence extends AppCompatActivity implements OnOptionSelected {

    private static final String TAG = "Teacherattendence";
    Spinner attendence_spinner_search,attdnce_spinner_class_search;
    private Toolbar mtoolbar;
    private RecyclerView recyclerView;
    private attendence_recycler adaptor;
    private List<attendence_modelclass> modelClassList;


    private SharedPreferenceEdit sharedPreferenceEdit;
    private String class_id;
    private JSONArray data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherattendence);

        mtoolbar = findViewById(R.id.teacher_attendence_toolbar);
        setSupportActionBar(mtoolbar);
        data=new JSONArray();
        getSupportActionBar().setTitle("Attendence");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.student_show);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        sharedPreferenceEdit=new SharedPreferenceEdit(Teacherattendence.this);

        class_id=sharedPreferenceEdit.GetClassId();
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
                        modelClassList.add(new attendence_modelclass(id, R.drawable.profileicon,name,roll,
                                semester_no,"A"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                adaptor = new attendence_recycler(Teacherattendence.this, modelClassList);
                adaptor.setOnOptionSelected(Teacherattendence.this);
                recyclerView.setAdapter(adaptor);
            }

            @Override
            public void OnFailure(String err) {
                Log.e("eheh", "OnFailure: "+err );

            }
        });




//        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Ahmed","Roll No. 12",
//                "Semester 4","Class A"));
//        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Ali","Roll No. 12",
//                "Semester 4","Class A"));
//        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Hamza","Roll No. 12",
//                "Semester 4","Class A"));
//
//        adaptor = new attendence_recycler(this, modelClassList);
//        adaptor.setOnOptionSelected(this);
//
//        recyclerView.setAdapter(adaptor);
    }

    public void SubmitAttendence(View view) throws JSONException {
        List<attendence_modelclass> l =new ArrayList<>();
        l=adaptor.getAttendenceModels();
//        for(int i=0;i<)
//        l.get(0).getStudent_id();
JSONArray js=new JSONArray();

        for(int i=0;i<l.size();i++){
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("student_id",l.get(i).getStudent_id());
            Log.e(TAG, l.get(i).getStudent_name()+"");
            jsonObject1.put("status",l.get(i).getSelectedPosition());
            //data.put("student",jsonObject1);
            js.put(jsonObject1);
            //Log.e(TAG, data+"");
        }



        Log.e(TAG, js + " "+l.size());
        JSONObject data=new JSONObject();
        data.put("attends",js);
        data.put("id",class_id);
        data.put("screen","StudentAttendence");
VolleyRequest.PostRequest(Teacherattendence.this, Urls.PostAttendance, data, new VolleyPostCallBack() {
    @Override
    public void OnSuccess(JSONObject jsonObject) {
        Intent intent=new Intent(Teacherattendence.this,Dashboard.class);
        startActivity(intent);
    }

    @Override
    public void OnFailure(String err) {

    }
});
    }


    @Override
    public void onOptionSelected(int position, int itemSelected) {
        modelClassList.get(position).setSelectedPosition(itemSelected);
        switch (itemSelected){
            case 1:
                modelClassList.get(position).setOp1Sel(true);
                Log.d(TAG, "Position "+position+" Selected");
                break;

            case 2:
                modelClassList.get(position).setOp2Sel(true);
                break;
            case 3:
                (modelClassList.get(position)).setOp3Sel(true);
                break;
        }
         adaptor.setAttendenceModels(modelClassList);
         adaptor.notifyDataSetChanged();
        // mRecyclerView.setAdapter(questionAdapter);
    }
}

