package com.example.akhlaqcommunication.emaddrassa.RecyclerClasses;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akhlaqcommunication.emaddrassa.Model.Modelclass;
import com.example.akhlaqcommunication.emaddrassa.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class parent_attendence_recycler extends RecyclerView.Adapter<parent_attendence_recycler.ViewHolder> {

    private Context context;
    private List<Modelclass> modelClassList;


    public parent_attendence_recycler(Context context, List<Modelclass> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.parent_attendance_recycler_item,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Modelclass modelClass = modelClassList.get(i);


        viewHolder.date.setText(modelClass.getDate());

        if (modelClassList.get(i).getStatus().equals("1"))
        {
            viewHolder.status.setTextColor(Color.GREEN);
            viewHolder.status.setText("Present");
    }else if (modelClassList.get(i).getStatus().equals("2"))
       {


        viewHolder.status.setTextColor(Color.RED);
        viewHolder.status.setText("Absent");
       }
        else if (modelClassList.get(i).getStatus().equals("3"))
        {


            viewHolder.status.setTextColor(Color.MAGENTA);
            viewHolder.status.setText("Late");
        }
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


         TextView date,status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);

        }
    }
}
