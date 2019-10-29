package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.akhlaqcommunication.emaddrassa.R;

public class ParentEvent extends AppCompatActivity {

    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_event);

        //top toolbar
        mtoolbar = findViewById(R.id.parent_event_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Events");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
