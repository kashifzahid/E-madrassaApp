package com.example.akhlaqcommunication.emaddrassa.RecyclerClasses;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akhlaqcommunication.emaddrassa.Model.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.ParentConsole.ParentDailyDiary;
import com.example.akhlaqcommunication.emaddrassa.R;

import java.util.List;

public class parent_dailydiary_recycler extends RecyclerView.Adapter<parent_dailydiary_recycler.ViewHolder> {

    private Context context;
    private List<Modelclass> modelClassList;



    public parent_dailydiary_recycler(Context context, List<Modelclass> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.parent_daily_diary_recycler_item,null);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Modelclass modelClass = modelClassList.get(i);

      viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(context, ParentDailyDiary.class);
              intent.putExtra("diary_id",modelClass.getId());
              context.startActivity(intent);
          }
      });

        viewHolder.date.setText(String.format("Date : %s", modelClass.getDate()));
        viewHolder.remarks.setText(String.format("Remarks : %s", modelClass.getRemarks()));
        viewHolder.teacher.setText(String.format("Teacher Name : %s", modelClass.getTeacher()));
        viewHolder.grade.setText(String.format("Grade : %s", modelClass.getGrade()));

    }


    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


         TextView date,teacher,grade,remarks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            teacher = itemView.findViewById(R.id.teacher_name);
            grade=itemView.findViewById(R.id.grade);
            remarks=itemView.findViewById(R.id.remarks);


        }


    }
}
