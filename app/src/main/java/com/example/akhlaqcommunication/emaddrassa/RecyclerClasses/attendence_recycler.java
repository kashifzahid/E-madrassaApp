package com.example.akhlaqcommunication.emaddrassa.RecyclerClasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akhlaqcommunication.emaddrassa.Interface.OnOptionSelected;
import com.example.akhlaqcommunication.emaddrassa.Model.AttendenceModel;
import com.example.akhlaqcommunication.emaddrassa.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class attendence_recycler extends RecyclerView.Adapter<attendence_recycler.ViewHolder> {

    private String TAG = "attendence_recycler";

    public int mSelectedItem = 1;
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

    private RadioGroup lastCheckedRadioGroup = null;
    ArrayList<String> selected = new ArrayList<>();

    public void setOnOptionSelected(OnOptionSelected onOptionSelected) {
        this.onOptionSelected = onOptionSelected;
    }

    private OnOptionSelected onOptionSelected;

    public List<attendence_modelclass> getAttendenceModels() {
        return modelClassList;
    }

    public void setAttendenceModels(List<attendence_modelclass> attendenceModels) {
        this.modelClassList = attendenceModels;
    }

    @Override
    public void onBindViewHolder(@NonNull final attendence_recycler.ViewHolder viewHolder, int position) {

        attendence_modelclass modelClass = modelClassList.get(position);
        viewHolder.proifle_image.setImageResource(R.drawable.profileicon);
        viewHolder.std_name.setText(modelClass.getStudent_name());
        viewHolder.std_roll_num.setText("Roll no : "+modelClass.getStudent_roll_number());
        viewHolder.std_semster.setText("Semester no : "+modelClass.getStudent_semster());
        viewHolder.std_class.setText("Class : "+modelClass.getStudent_class());
//        viewHolder.radio_present.setChecked( position == mSelectedItem);
//        viewHolder.radio_absent.setChecked(position == mSelectedItem);
//        viewHolder.radio_late.setChecked(position == mSelectedItem);

        Log.e("POSITION" + position, "1" + modelClass.isOp1Sel());

        Log.e("POSITION" + position, "2" + modelClass.isOp2Sel());
        Log.e("POSITION" + position, "3" + modelClass.isOp3Sel());


        viewHolder.radio_present.setChecked(modelClass.isOp1Sel());
        viewHolder.radio_absent.setChecked(modelClass.isOp2Sel());
        viewHolder.radio_late.setChecked(modelClass.isOp3Sel());


    }

    @Override
    public int getItemCount() {
        if (modelClassList != null) {
            return modelClassList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView proifle_image;
        TextView std_name,std_roll_num,std_semster,std_class;
        RadioButton radio_present,radio_absent,radio_late;
        RadioGroup radioGroupatten;

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

                    switch (v.getId()) {
                        case R.id.present:
                            onOptionSelected.onOptionSelected(getAdapterPosition(), 1);
                            Log.d(TAG, "Position "+getAdapterPosition()+" Selected");
                            break;

                        case R.id.absent:
                            onOptionSelected.onOptionSelected(getAdapterPosition(), 2);
                            break;

                        case R.id.late:
                            onOptionSelected.onOptionSelected(getAdapterPosition(), 3);
                            break;
                    }
                }
            };

            radio_present.setOnClickListener(clickListener);
            radio_absent.setOnClickListener(clickListener);
            radio_late.setOnClickListener(clickListener);

        }
    }
}
