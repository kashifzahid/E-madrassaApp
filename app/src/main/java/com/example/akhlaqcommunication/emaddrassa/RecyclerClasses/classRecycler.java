package com.example.akhlaqcommunication.emaddrassa.RecyclerClasses;


import android.content.Context;
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

public class classRecycler extends RecyclerView.Adapter<classRecycler.ViewHolder> {

    private Context context;
    private List<Modelclass> modelClassList;


    public classRecycler(Context context, List<Modelclass> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_daily_diary,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Modelclass modelClass = modelClassList.get(i);

        viewHolder.proifle_image.setImageResource(R.drawable.profileicon);
        viewHolder.std_name.setText(modelClass.getStudent_name());
        viewHolder.std_roll_num.setText(modelClass.getStudent_roll_number());
        viewHolder.std_semster.setText(modelClass.getStudent_semster());
        viewHolder.std_class.setText(modelClass.getStudent_class());

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         CircleImageView proifle_image;
         TextView std_name,std_roll_num,std_semster,std_class;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            proifle_image = itemView.findViewById(R.id.std_profile_image);
            std_name = itemView.findViewById(R.id.student_name);
            std_roll_num = itemView.findViewById(R.id.student_roll_number);
            std_semster = itemView.findViewById(R.id.student_semester);
            std_class = itemView.findViewById(R.id.student_class);
        }
    }
}
