package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.akhlaqcommunication.emaddrassa.R;

public class TeacherResult extends AppCompatActivity {

    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_result);

        //top toolbar
        mtoolbar = findViewById(R.id.teacher_result_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Results");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
