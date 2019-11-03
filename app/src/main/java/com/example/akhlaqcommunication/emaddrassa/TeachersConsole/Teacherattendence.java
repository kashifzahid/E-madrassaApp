package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

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
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.attendence_modelclass;
import com.example.akhlaqcommunication.emaddrassa.RecyclerClasses.attendence_recycler;

import java.util.ArrayList;
import java.util.List;

public class Teacherattendence extends AppCompatActivity implements OnOptionSelected {

    private static final String TAG = "Teacherattendence";
    Spinner attendence_spinner_search,attdnce_spinner_class_search;
    private Toolbar mtoolbar;
    private RecyclerView recyclerView;
    private attendence_recycler adaptor;
    private List<attendence_modelclass> modelClassList;

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
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);


//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                        recyclerLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Ahmed","Roll No. 12",
                "Semester 4","Class A"));
        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Ali","Roll No. 12",
                "Semester 4","Class A"));
        modelClassList.add(new attendence_modelclass(R.drawable.profileicon,"Hamza","Roll No. 12",
                "Semester 4","Class A"));

        adaptor = new attendence_recycler(this, modelClassList);
        adaptor.setOnOptionSelected(this);

        recyclerView.setAdapter(adaptor);
    }

    public void SubmitAttendence(View view) {
        List<attendence_modelclass> l =new ArrayList<>();
        l=adaptor.getAttendenceModels();

        Log.e(TAG, l.get(0).getSelectedPosition() + " ");
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

