package com.example.akhlaqcommunication.emaddrassa.RecyclerClasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.akhlaqcommunication.emaddrassa.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class attendence_recycler extends RecyclerView.Adapter<attendence_recycler.ViewHolder> {
    public int mSelectedItem = -1;
    private Context context;
    private List<attendence_modelclass> modelClassList;

    public attendence_recycler(Context context, List<attendence_modelclass> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.show_student_attendence_rec,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull attendence_recycler.ViewHolder viewHolder, int i) {

        attendence_modelclass modelClass = modelClassList.get(i);
        viewHolder.proifle_image.setImageResource(R.drawable.profileicon);
        viewHolder.std_name.setText(modelClass.getStudent_name());
        viewHolder.std_roll_num.setText(modelClass.getStudent_roll_number());
        viewHolder.std_semster.setText(modelClass.getStudent_semster());
        viewHolder.std_class.setText(modelClass.getStudent_class());
        viewHolder.radio_present.setChecked( i == mSelectedItem);
        viewHolder.radio_absent.setChecked(i == mSelectedItem);
        viewHolder.radio_late.setChecked(i == mSelectedItem);

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView proifle_image;
        TextView std_name,std_roll_num,std_semster,std_class;
        RadioButton radio_present,radio_absent,radio_late;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            proifle_image = itemView.findViewById(R.id.std_profile_image);
            std_name = itemView.findViewById(R.id.student_name);
            std_roll_num = itemView.findViewById(R.id.student_roll_number);
            std_semster = itemView.findViewById(R.id.student_semester);
            std_class = itemView.findViewById(R.id.student_class);
            radio_present = itemView.findViewById(R.id.present);
            radio_absent = itemView.findViewById(R.id.absent);
            radio_late = itemView.findViewById(R.id.late);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                }
            };
            radio_present.setOnClickListener(clickListener);
            radio_absent.setOnClickListener(clickListener);
            radio_late.setOnClickListener(clickListener);


        }
    }
}
