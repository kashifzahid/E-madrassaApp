package com.example.akhlaqcommunication.emaddrassa.ParentConsole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.DailyDiary;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.TeacherResult;

public class ParentDashboard extends AppCompatActivity {

    private Toolbar mtoolbar;
    Button parentdailydiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_dashboard);

        mtoolbar = findViewById(R.id.parent_dashboard_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Dashboard");


    }
    public void openevents(View v) {
        Intent openattnds = new Intent(getApplicationContext(), ParentEvent.class);
        startActivity(openattnds);
    }

    public void openfee(View v) {

        Intent openattnds = new Intent(getApplicationContext(),ParentFee.class);
        startActivity(openattnds);

    }

    public void openresult(View v) {
        Intent openattnds = new Intent(getApplicationContext(),ParentResult.class);
        startActivity(openattnds);

    }

    public void openPerformnce(View v) {
        Intent openattnds = new Intent(getApplicationContext(),PerformenceParent.class);
        startActivity(openattnds);

    }

    public void opendailydiary(View v) {
        Intent opendiary = new Intent(getApplicationContext(), ParentDailyDiary.class);
        startActivity(opendiary);
    }

    public void openparentattendence(View v) {
        Intent opendiary = new Intent(getApplicationContext(), Parent_attendence.class);
        startActivity(opendiary);
    }

}
