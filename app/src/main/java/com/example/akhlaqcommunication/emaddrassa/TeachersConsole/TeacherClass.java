package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.classRecycler;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.Modelclass;

import java.util.ArrayList;
import java.util.List;

public class TeacherClass extends AppCompatActivity {

    private Toolbar mtoolbar;
    private RecyclerView recyclerView;
    private classRecycler adaptor;
    List<Modelclass> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class);

        mtoolbar = findViewById(R.id.teacher_class_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Class");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.student_preview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelClassList.add(new Modelclass(R.drawable.profileicon,"Ahmed","Roll No. 12",
                "Semester 4","Class A"));

        adaptor = new classRecycler(this, modelClassList);
        recyclerView.setAdapter(adaptor);
    }
}
