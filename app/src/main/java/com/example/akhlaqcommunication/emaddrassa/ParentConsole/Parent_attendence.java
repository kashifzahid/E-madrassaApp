package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.akhlaqcommunication.emaddrassa.R;

public class Parent_attendence extends AppCompatActivity {

    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_attendence);

        //top toolbar
        mtoolbar = findViewById(R.id.parent_attendence_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Attendence");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
