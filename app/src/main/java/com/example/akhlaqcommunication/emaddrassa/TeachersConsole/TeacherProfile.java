package com.example.akhlaqcommunication.emaddrassa.TeachersConsole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class TeacherProfile extends AppCompatActivity {
    private SharedPreferenceEdit sharedPreferenceEdit;
    private TextView name,phone;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        sharedPreferenceEdit=new SharedPreferenceEdit(TeacherProfile.this);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        id=sharedPreferenceEdit.GetDriverId();

        GetProfile(id);


    }

    private void GetProfile(String id) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","TeacherProfile");
            Log.e("tag", "getProfile: "+id );
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyRequest.PostRequest(this, Urls.TeacherDashboard,jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                try {
                    String n=jsonObject.getString("TeacherName");
                    String c=jsonObject.getString("contact");
                    name.setText(n);
                    phone.setText(c);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void OnFailure(String err) {

            }
        });
    }

    public void LogOut(View view) {
        sharedPreferenceEdit.ClearDriverId();
        Intent intent=new Intent(TeacherProfile.this,TeacherLogin.class);
        startActivity(intent);

    }
}
