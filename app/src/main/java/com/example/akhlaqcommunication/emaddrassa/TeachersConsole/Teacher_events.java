package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.akhlaqcommunication.emaddrassa.R;

public class Teacher_events extends AppCompatActivity {

    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_events);

        //top toolbar
        mtoolbar = findViewById(R.id.teacher_event_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Events");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
