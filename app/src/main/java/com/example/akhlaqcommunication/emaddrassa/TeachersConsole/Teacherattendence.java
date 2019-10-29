package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.attendence_modelclass;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.attendence_recycler;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.classRecycler;

import java.util.ArrayList;
import java.util.List;

public class Teacherattendence extends AppCompatActivity {

    Spinner attendence_spinner_search,attdnce_spinner_class_search;
    private Toolbar mtoolbar;
    private RecyclerView recyclerView;
    private attendence_recycler adaptor;
    List<attendence_modelclass> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherattendence);

        mtoolbar = findViewById(R.id.teacher_attendence_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Attendence");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.student_show);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Ahmed","Roll No. 12",
                "Semester 4","Class A","Present","Absent","Late"));
        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Ali","Roll No. 12",
                "Semester 4","Class A","Present","Absent","Late"));
        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Hamza","Roll No. 12",
                "Semester 4","Class A","Present","Absent","Late"));
        adaptor = new attendence_recycler(this, modelClassList);
        recyclerView.setAdapter(adaptor);
    }

}

